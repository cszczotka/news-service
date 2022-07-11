package repository

import anorm.SqlStringInterpolation
import model.Event
import play.api.db.Database

import javax.inject.{Inject, Singleton}
import scala.concurrent.Future

@Singleton
class NewsRepository @Inject() (db: Database, dec: DatabaseExecutionContext) {

  private val simple = {
      case id ~ author ~ title ~ description ~ eventImage ~ publishedAt ~ content =>
        Event(id, author, title, description, eventImage, publishedAt, content)
  }


  def updateSomething(): Unit = {
    Future {
      db.withConnection { conn =>
        // do whatever you need with the db connection
      }
    }(dec)
  }

  def findById(id: Long): Future[Option[Event]] = Future {
    db.withConnection { implicit connection =>
      SQL"select * from events where id = $id".as(simple)
    }
  }(dec)

}