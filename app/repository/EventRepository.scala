package repository

import anorm.{Row, SimpleSql, SqlStringInterpolation, ~}
import model.{Event, EventThin}
import play.api.db.Database

import javax.inject.{Inject, Singleton}
import scala.concurrent.Future
import anorm.SqlParser.get

import scala.language.postfixOps

@Singleton
class EventRepository @Inject()(db: Database, dec: DatabaseExecutionContext) {

  val rowParser: anorm.RowParser[Event] = {
      get[String]("id") ~
      get[String]("author") ~
      get[String]("title") ~
      get[String]("description") ~
      get[String]("eventImage") ~
      get[String]("publishedAt") ~
      get[String]("content") map {
      case id ~ author ~ title ~ description ~ eventImage ~ publishedAt ~ content
        => Event(id, author, title, description, eventImage, publishedAt, content)
    }
  }

  val thinRowParser: anorm.RowParser[EventThin] = {
    get[String]("id") ~
    get[String]("author") ~
    get[String]("title") ~
    get[String]("publishedAt") map {
      case id ~ author ~ title ~ publishedAt
      => EventThin(id, author, title, publishedAt)
    }
  }

  def findLastN(n: Int): Seq[EventThin] = {
    db.withConnection { implicit c =>
      val query: SimpleSql[Row] = SQL"""
        select id, author, title, publishedAt from event order by publishedAt
      """
      query.as(thinRowParser *).take(n)
    }
  }

  def findById(id: String): Event = {
    db.withConnection { implicit c =>
      val query: SimpleSql[Row] = SQL"""
        select * from event where id = $id
      """
      query.as(rowParser *).head
    }
  }

  def insertNewEvent(event: Event): Unit = db.withTransaction { implicit c =>
    val insertSql = SQL"""
        insert into event (id, title, author, description, eventImage, content, publishedAt)
        values (${event.id}, ${event.title}, ${event.author}, ${event.description}, ${event.eventImage},
        ${event.content},${event.publishedAt})
    """
    insertSql.executeInsert()
  }
}