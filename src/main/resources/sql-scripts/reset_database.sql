BEGIN;
set foreign_key_checks = 0;

drop table if exists links;
drop table if exists sessions;
drop table if exists userPictures;
drop table if exists users;
drop table if exists wishes;
drop table if exists wishlists;
drop table if exists wishPictures;

set foreign_key_checks = 1;

create table users
(
    id             int auto_increment primary key,
    email          varchar(255) not null,
    fullName       varchar(255) not null,
    argon2Password varchar(255) not null,
    created        datetime     not null,
    pictureData    blob         null,
    constraint email
        unique (email)
)
    charset = UTF8MB3;

create table wishlists
(
    id      int auto_increment primary key,
    userId  int          not null,
    name    varchar(255) not null,
    created datetime     not null,
    constraint FKWishlistsUserId
        foreign key (userId) references users (id)
            on delete cascade
)
    charset = UTF8MB4;

create table links
(
    id           int auto_increment primary key,
    wishlistId   int          not null,
    resourcePath varchar(255) not null,
    created      datetime     not null,
    constraint FKLinksWishlistId
        foreign key (wishlistId) references wishlists (id)
            on delete cascade
)
    charset = UTF8MB4;

create table wishes
(
    id          int auto_increment primary key,
    wishlistId  int           not null,
    created     datetime      not null,
    title       varchar(255)  not null,
    price       decimal(9, 2) null,
    url         varchar(2048) null,
    note        varchar(5000) null,
    pictureData blob          null,
    constraint FKWishesWishlistId
        foreign key (wishlistId) references wishlists (id)
            on delete cascade
)
    charset = UTF8MB4;

COMMIT;