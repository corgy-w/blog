create table users
(
    id       int auto_increment comment '用户ID'
        primary key,
    name     varchar(255) not null comment '用户名',
    username varchar(255) not null comment '用户账号',
    password varchar(255) not null comment '用户密码',
    mail     varchar(30)  not null comment '用户邮箱',
    phone    varchar(20)  not null comment '用户电话',
    sex      varchar(10)  not null comment '用户性别',
    status   varchar(20)  not null comment '用户状态'
);


