create table roles
(
    id               int auto_increment comment '角色ID'
        primary key,
    roleName         varchar(20)  not null comment '角色名称',
    roleIntroduction varchar(100) not null comment '角色介绍'
);


