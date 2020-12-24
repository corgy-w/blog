# 初代blog后端接口

## 介绍

为了满足自己无所事事的情况 开始了没有前端纯靠postman的后端开发 里面还有好多接口因为没有前端没法实地进行验证 同时有许多小bug

主要技术：springboot+springSecurity+Mybatis+lettuce

## TODO

- 评论更改需自动更改时间
- 登陆退出操作需要完整
- 重写LoginUser 完善api的信息
- 完善jwt登陆
- 记录登录的ip的地址 进行分析

## 架构

springboot(2.3.5.RELEASE)、mybatis-spring-boot(2.1.4)、mysql(8.0.22)、redis(3.12)等.

### 权限

实现了角色控制 通过赋予角色进行不同的操作 认证方式实现了json web token的状态认证方式

### 登录记录

通过aop环绕管理用户的访问 记录文章的访问次数

### 日志

使用logback进行每天运行的统计 记录登录ip 