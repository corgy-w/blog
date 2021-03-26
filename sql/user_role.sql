create table user_role
(
    userId int not null comment '用户ID',
    roleId int not null comment '角色ID',
    constraint user_role_pk
        unique (userId, roleId),
    constraint fk_role_id
        foreign key (roleId) references roles (id)
            on update cascade on delete cascade,
    constraint fk_user_id
        foreign key (userId) references users (id)
            on update cascade on delete cascade
);


