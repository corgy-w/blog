create table types
(
    id           int auto_increment comment '类型ID'
        primary key,
    name         varchar(30)  not null comment '类型名称',
    introduction varchar(100) null comment '类型简介'
);


