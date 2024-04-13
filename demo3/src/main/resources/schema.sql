create table user_info
(
    id          bigint unsigned auto_increment comment '主键'
        primary key,
    username    varchar(32) null comment '用户名',
    phone_num   varchar(32) null comment '手机号',
    password    varchar(128) null comment '密码（密文）',
    name        varchar(32) null comment '姓名',
    age         int null comment '年龄',
    email       varchar(64) null comment '邮箱',
    create_time datetime default CURRENT_TIMESTAMP null comment '创建时间',
    update_time datetime default CURRENT_TIMESTAMP null comment '最后修改时间'
)
    comment '用户信息表';

----
create table if not exists log_operation
(
    id               bigint auto_increment comment '主键'
    primary key,
    app_id           varchar(32)                           not null comment '应用id',
    version          varchar(64)                           null comment '应用版本',
    instance_id      varchar(64)                           null comment '操作服务器节点标识（支持集群时用于定位具体哪台服务器执行）',
    user_id          varchar(64)                           not null comment '用户标识',
    user_name        varchar(64)                           null comment '用户名',
    user_real_name   varchar(128)                          null comment '用户真实姓名',
    user_org_id      varchar(64)                           null comment '用户组标识',
    user_org_name    varchar(64)                           null comment '用户组名',
    terminal_type    int                                   not null comment '终端类型。0:服务内部定时任务等触发；1:浏览器；2:客户端；3:移动App；4:小程序。推荐前端支持多语言',
    terminal_address varchar(64)                           null comment '操作者所在终端地址，如 IPv4(15) IPv6(46)',
    terminal_id      varchar(64)                           null comment '操作者所在终端标识，如PC的 MAC；手机的 IMSI、IMEI、ESN、MEID；甚至持久化的 UUID',
    terminal_info    varchar(255)                          null comment '操作者所在终端信息，如操作系统类型、浏览器、版本号等',
    object_type      varchar(128)                          null comment '操作对象类型；建议支持多语言',
    object_id        varchar(128)                          null comment '操作对象id',
    object_name      varchar(255)                          null comment '操作对象名称',
    operation_param  text                                  null comment '触发该操作的参数',
    operation        varchar(255)                          not null comment '操作动作；建议支持多语言',
    detail           text                                  null comment '操作详情。详细的描述用户的操作内容、json对象等',
    detail_key       varchar(128)                          null comment '操作详情对应的多语言key',
    detail_item      varchar(255)                          null comment '填充 detail_i18n_key 对应的多语言翻译。数组类型',
    result           int                                   not null comment '操作结果,0成功；1失败；2部分成功；建议支持多语言',
    error_code       varchar(32)                           null comment '错误码',
    operation_time   timestamp                             not null comment '操作触发时间，注意采集完成后替换为日志服务所在服务器时间',
    end_time         timestamp                             null comment '操作结束时间',
    duration         bigint                                null comment '操作持续时间，冗余字段，单位 ms',
    trace_id         varchar(64)                           null comment '调用链id',
    relation_id      varchar(64)                           null comment '关联的调用链id/业务id',
    tenant_code      varchar(20) default ''                null comment '租户编码',
    create_time      timestamp   default CURRENT_TIMESTAMP null comment '数据入库时间',
    extended_field0  varchar(1024)                         null,
    extended_field1  varchar(1024)                         null,
    extended_field2  varchar(1024)                         null,
    extended_field3  varchar(1024)                         null,
    extended_field4  varchar(1024)                         null
    )
    comment '业务日志';

create index idx_operation_time
    on log_operation (operation_time);

create index idx_terminal_address
    on log_operation (terminal_address);

create index idx_trace_id
    on log_operation (trace_id);

create index idx_user_id
    on log_operation (user_id);

----
create table if not exists crypto_info
(
    app_id        varchar(32)                           not null comment '应用标识',
    header        varchar(32) default ''                not null comment '密文前缀/算法标识/版本标志',
    data_key      varchar(64)                           not null comment '数据密钥（密文）',
    root_key_part varchar(512)                          null comment '根密钥部件',
    vector        varchar(64)                           null comment '初始偏移向量',
    create_time   datetime    default CURRENT_TIMESTAMP null comment '创建时间',
    primary key (app_id, header)
    )
    comment '加密元信息';

----------
create table if not exists persistent_logins
(
    username  varchar(64) not null,
    series    varchar(64) not null
    primary key,
    token     varchar(64) not null,
    last_used timestamp   not null
    );
