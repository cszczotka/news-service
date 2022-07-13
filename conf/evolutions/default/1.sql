-- !Ups
set ignorecase true;

create table event (
    id varchar(255) NOT NULL,
    title varchar(255) NOT NULL,
    author varchar(255) NOT NULL,
    publishedAt varchar(255) NOT NULL,
    description varchar(255),
    eventImage varchar(255),
    content varchar(255),
    primary key (id)
);

-- !Downs

drop table if exists event;