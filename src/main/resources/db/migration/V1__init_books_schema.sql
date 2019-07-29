create table category
(
    category_cd     varchar(10)     primary key not null,
    category_name   varchar(80)                 not null,
    category_desc   varchar(256)
);

create table publisher
(
    publisher_cd    varchar(10)     primary key not null,
    publisher_name  varchar(80)                 not null,
    publisher_desc  varchar(256)
);

create table author
(
    author_cd       varchar(10)     primary key not null,
    author_name     varchar(80)                 not null,
    author_address  varchar(256),
    author_ph_no    varchar(80)
);

create table book
(
    id              int8            primary key not null,
    title           varchar(256)    unique      not null,
    isbn            int8,
    author_cd       varchar(10)                 not null,
    publisher_cd    varchar(10)                 not null,
    category_cd     varchar(10)                 not null,
    published_date  date,
    price           decimal
);

create index idx1 on book (
    id
);

alter table book
    add constraint book_fk1 foreign key (author_cd)
        references author (author_cd) on delete restrict on update restrict;

alter table book
    add constraint book_fk2 foreign key (publisher_cd)
        references publisher (publisher_cd) on delete restrict on update restrict;

alter table book
	add constraint book_fk3 foreign key (category_cd)
        references category (category_cd) on delete restrict on update restrict;

create sequence book_id_seq
    increment 1
    minvalue 1
    maxvalue 9223372036854775807
    start 1
    cache 1
    owned by book.id;

alter table book
    alter column id set default nextval('book_id_seq');