create table comments
(
    id          int auto_increment
        primary key,
    comContent  varchar(255)                        not null,
    articleId   int                                 not null,
    userId      int                                 not null,
    commentTime timestamp default CURRENT_TIMESTAMP not null,
    constraint fk_articleId
        foreign key (articleId) references articles (id),
    constraint fk_userId
        foreign key (userId) references users (id)
);


