----
-- Demo
----
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
-- Shoulder operationLog
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
    insert_time      timestamp   default CURRENT_TIMESTAMP null comment '数据入库时间',
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
-- Shoulder crypto
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


----
-- Spring Security RememberMe
----
create table if not exists persistent_logins
(
    username  varchar(64) not null,
    series    varchar(64) not null
    primary key,
    token     varchar(64) not null,
    last_used timestamp   not null
    );

----
-- Spring Oauth2
----
create table if not exists oauth_access_token
(
    token_id          varchar(255) not null comment 'access_token的MD5值'
    primary key,
    token             blob         null comment 'OAuth2AccessToken 对象序列化后的二进制数据。真实的AccessToken',
    authentication_id varchar(255) null comment '唯一性, 其值是根据当前的username(如果有),client_id与scope通过MD5生成的. 具体实现参考DefaultAuthenticationKeyGenerator.java',
    user_name         varchar(255) null comment '登录时的用户名。若客户端没有用户名,则该值等于client_id',
    client_id         varchar(255) null comment '对应 oauth_client_details 主键',
    authentication    blob         null comment 'OAuth2Authentication.java对象序列化后的二进制数据.',
    refresh_token     varchar(255) null comment '关联refresh_token表主键。refresh_token的值的 MD5',
    create_time       datetime     null comment '自行扩展字段：创建时间',
    constraint authentication_id_index
    unique (authentication_id),
    constraint refresh_token_index
    unique (refresh_token),
    constraint user_name_index
    unique (user_name)
    )
    comment 'access_token表。见JdbcTokenStore.java. ';

create index client_id_index
    on oauth_access_token (client_id);

create table if not exists oauth_approvals
(
    userId         varchar(256) null,
    clientId       varchar(256) null,
    scope          varchar(256) null,
    status         varchar(10)  null,
    expiresAt      timestamp    null,
    lastModifiedAt timestamp    null
    );

create table if not exists oauth_client_details
(
    client_id               varchar(255)                 not null comment '唯一的clientId'
    primary key,
    resource_ids            varchar(255)                 null comment '客户端所能访问的资源id集合。多个资源时用逗号(,)分隔.一般为大类，不应该很细',
    client_secret           varchar(255)                 null comment '客户端秘钥。也称作 appSecret',
    scope                   varchar(255)                 null comment '客户端申请的权限范围。如读取用户信息，分享到空间，发表说说等',
    authorized_grant_types  varchar(255)                 null comment '客户端支持的grant_type。可选值包括authorization_code,password,refresh_token,implicit,client_credentials, 若支持多个grant_type用逗号(,)分隔,如: "authorization_code,password".',
    web_server_redirect_uri varchar(255)                 null comment '客户端的重定向URI。可空, 当grant_type为authorization_code或implicit时, 在Oauth的流程中会使用并检查与注册时填写的redirect_uri是否一致.',
    authorities             varchar(255)                 null comment '客户端所拥有的Spring Security的权限值。可选, 若有多个权限值,用逗号(,)分隔, 如: "ROLE_UNITY,ROLE_USER".',
    access_token_validity   int                          null comment '客户端的access_token的有效时间值(单位:秒)。可选, 若不设定值则使用默认的有效时间值(60 * 60 * 12, 12小时).',
    refresh_token_validity  int                          null comment '客户端的refresh_token的有效时间值(单位:秒)。可选, 若不设定值则使用默认的有效时间值(60 * 60 * 24 * 30, 30天).',
    additional_information  text                         null comment '预留的字段,在Oauth的流程中没有实际的使用,可选,但若设置值,必须是JSON格式的数据。在实际应用中, 可以用该字段来存储关于客户端的一些其他信息,如客户端的国家,地区,注册时的IP地址等等.',
    autoapprove             varchar(255) default 'false' null comment '是否跳过用户同意授权页面, 默认值为 ''false'', 可选值包括 ''true'',''false'', ''read'',''write''.',
    active                  tinyint(1)   default 1       null comment '自行扩展的：是否启用，即实现逻辑删除或停用，以及人工验证信息通过后再启用',
    create_time             datetime                     null comment '自行扩展的：插入时间'
    )
    comment '用于存储可以访问本授权服务器的客户端信息表。见JdbcClientDetailsService';

create table if not exists oauth_client_token
(
    token_id          varchar(256) null,
    token             blob         null,
    authentication_id varchar(128) not null
    primary key,
    user_name         varchar(256) null,
    client_id         varchar(256) null
    );

create table if not exists oauth_code
(
    code           varchar(255) null comment '存储服务端系统生成的code的值(未加密).',
    authentication blob         null comment 'AuthorizationRequestHolder.java对象序列化后的二进制数据.'
    )
    comment '授权码。仅授权码模式使用该表：用于换accessToken。见JdbcAuthorizationCodeServices';

create index code_index
    on oauth_code (code);

create table if not exists oauth_refresh_token
(
    token_id       varchar(255) null comment 'refresh_token的值的MD5',
    token          blob         null comment 'OAuth2RefreshToken.java对象序列化后的二进制数据.',
    authentication blob         null comment 'OAuth2Authentication.java对象序列化后的二进制数据.',
    create_time    datetime     null comment '自行扩展的：插入时间'
    )
    comment 'refresh_token表。见JdbcTokenStore';

create index token_id_index
    on oauth_refresh_token (token_id);

