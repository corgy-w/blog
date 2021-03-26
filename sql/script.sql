create table articles
(
    id          int auto_increment comment '文章ID'
        primary key,
    title       varchar(30)  not null comment '文章标题',
    context     longtext     not null comment '文章内容',
    preview     varchar(100) not null comment '文章预览',
    images      varchar(100) null comment '缩略链接',
    releaseTime timestamp    not null comment '发布时间',
    readNum     int          not null comment '阅读数',
    userId      int          not null comment '用户ID',
    typeId      int          not null comment '类型ID',
    constraint typeid
        foreign key (typeId) references types (id),
    constraint userid
        foreign key (userId) references users (id)
);


