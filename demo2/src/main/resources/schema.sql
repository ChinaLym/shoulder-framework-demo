CREATE TABLE IF NOT EXISTS user_info
(
    id          bigint unsigned auto_increment comment '主键'
        primary key,
    name        varchar(64)                        not null comment '昵称',
    sex         int                                not null comment '性别: 0:未知；1：男性；2：女性',
    age         int                                not null comment '年龄',
    birth       date                               not null comment '出生日期',
    level       int                                not null comment '用户等级/权重 1-低，2-中低，3-中，4-中高，5-高',
    id_card     varchar(64)                        null,
    real_name   varchar(128)                       null comment '真实姓名',
    initials    varchar(128)                       null comment '真实姓名-首字母',
    spellings   varchar(255)                       null comment '真实姓名-汉语拼音全拼',
    phone_num   varchar(32)                        null comment '手机号',
    email       varchar(128)                       null comment '邮箱',
    status      int      default 0                 not null comment '用户是否启用：0-正常；1-禁用 2- 删除',
    del         int      default 0                 not null comment '仅用于demo，演示 OmitById，0-未删除，其他-已删除，本demo里，删除后值会同id',
    group_auth  int                                null comment '是否校验用户所属组/部门权限',
    group_id    int                                null comment '用户所属组/部门id',
    group_name  varchar(64)                        not null comment '用户组名称',
    group_path  varchar(255)                       null comment '用户所属组路径',
    creator     bigint                             not null comment '创建人编号',
    create_time datetime default CURRENT_TIMESTAMP null comment '创建时间',
    update_time datetime default CURRENT_TIMESTAMP null comment '最后修改时间',
    description varchar(255)                       null comment '用户描述'
)
    comment '用户信息表';

-- SET @index_exists = (SELECT COUNT(*) FROM information_schema.statistics
--                      WHERE table_schema = DATABASE()
--                      AND table_name = 'user_info'
--                      AND index_name = 'idx_name');
-- 
-- IF @index_exists = 0 THEN
-- CREATE INDEX idx_name ON user_info (name);
-- END IF;

CREATE INDEX IF NOT EXISTS idx_name ON user_info (name);
CREATE INDEX IF NOT EXISTS idx_phone_num ON user_info (phone_num);
CREATE INDEX IF NOT EXISTS idx_email ON user_info (email);

----

CREATE TABLE IF NOT EXISTS tb_sequence
(
    name          varchar(64) comment '主键：序列号名（业务标记）' primary key,
    min_value     int      default 0                 not null comment '最小值：达到最大值后会重置为min_value作为初始值',
    max_value     int      default not null comment '最大值：序号不会比该值更大，达到后将重置',
    step          int                                not null comment 'step',
    current_value int                                not null comment 'current_value',
    create_time   datetime default CURRENT_TIMESTAMP null comment '创建时间',
    update_time   datetime default CURRENT_TIMESTAMP null comment '最后修改时间',
    description   varchar(255)                       null comment '用户描述'
)
    comment 'sequence';
--     constraint uk_sequence_name unique (name)


CREATE TABLE IF NOT EXISTS log_operation
(
    id               bigint auto_increment comment '主键' primary key,
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
    object_name      varchar(128)                          null comment '操作对象名称',
    operation_param  text                                  null comment '触发该操作的参数, json 格式',
    operation        varchar(128)                          not null comment '操作动作；建议支持多语言',
    detail           text                                  null comment '操作详情。详细的描述用户的操作内容、json对象，仅在深入排差时查看',
    detail_i18n_key  varchar(128)                          null comment '操作详情对应的多语言key',
    detail_i18n_item varchar(255)                          null comment '填充 detail_i18n_key 对应的多语言翻译。数组类型',
    result           int                                   not null comment '操作结果,0成功；1失败；2部分成功；建议支持多语言',
    error_code       varchar(32)                           null comment '错误码',
    operation_time   timestamp                             not null comment '操作触发时间，注意采集完成后替换为日志服务所在服务器时间',
    end_time         timestamp                             null comment '操作结束时间',
    duration         bigint                                null comment '操作持续时间，冗余字段，单位 ms',
    trace_id         varchar(64)                           null comment '调用链id',
    relation_id      varchar(64)                           null comment '关联的调用链id/业务id',
    tenant_code      varchar(20) default ''                null comment '租户编码',
    create_time      timestamp   default CURRENT_TIMESTAMP null comment '数据入库时间',
    update_time      timestamp   default CURRENT_TIMESTAMP null comment '数据更新时间，日志表在非必要的订正前提下，一般不更新',
    extended_field0  varchar(1024)                         null,
    extended_field1  varchar(1024)                         null,
    extended_field2  varchar(1024)                         null,
    extended_field3  varchar(1024)                         null,
    extended_field4  varchar(1024)                         null
)
    comment '业务日志';

CREATE INDEX IF NOT EXISTS idx_operation_time
    on log_operation (operation_time);

CREATE INDEX IF NOT EXISTS idx_terminal_address
    on log_operation (terminal_address);

CREATE INDEX IF NOT EXISTS idx_trace_id
    on log_operation (trace_id);

CREATE INDEX IF NOT EXISTS idx_user_id
    on log_operation (user_id);

----
CREATE TABLE IF NOT EXISTS crypto_info
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

CREATE TABLE IF NOT EXISTS tb_tag
(
    id             bigint unsigned auto_increment comment '主键'
        primary key,
    biz_id         varchar(64)                               not null comment '业务唯一标识(不可修改；业务键拼接并哈希)',
    delete_version bigint unsigned default 0                 not null comment '删除标记：0-未删除；否则为删除时间',
    version        int             default 0                 not null comment '数据版本号：用于幂等防并发',
    display_order  int                                       not null comment '展示顺序',
    tenant         varchar(32)                               not null comment '租户',
    biz_type       varchar(64)                               not null comment '配置类型，通常可据此分库表',
    name           varchar(64)                               not null comment '标签名称',
    display_name   varchar(64)                               not null comment '标签名称-展示',
    description    varchar(255)                              null comment '备注:介绍为啥添加这一条',
    icon           varchar(255)                              null comment '图标地址',
    source         varchar(64)                               null comment '来源',
    status         varchar(32)                               null comment '状态',
    creator        varchar(64)                               not null comment '创建人编号',
    create_time    datetime        default CURRENT_TIMESTAMP not null comment '创建时间',
    modifier       varchar(64)                               not null comment '最近修改人编码',
    update_time    datetime        default CURRENT_TIMESTAMP not null comment '最后修改时间',
    ext            text                                      null comment '业务数据，json 类型'
)
    comment '标签表';

CREATE INDEX IF NOT EXISTS idx_tag_uni_bizid_tenant on tb_tag (biz_id, tenant);
CREATE INDEX IF NOT EXISTS idx_tag_uni_type_name_tenant on tb_tag (biz_type, name, tenant);

CREATE TABLE IF NOT EXISTS tb_tag_mapping
(
    id             bigint unsigned auto_increment comment '主键'
        primary key,
    tag_id         varchar(32)                               not null comment '业务唯一标识(不可修改；业务键拼接并哈希)',
    delete_version bigint unsigned default 0                 not null comment '删除标记：0-未删除；否则为删除时间',
    ref_type       varchar(64)                               not null comment 'refType',
    oid            varchar(64)                               not null comment 'ref object Id',
    creator        varchar(64)                               not null comment '创建人编号',
    create_time    datetime        default CURRENT_TIMESTAMP null comment '创建时间',
    modifier       varchar(64)                               not null comment '最近修改人编码',
    update_time    datetime        default CURRENT_TIMESTAMP null comment '最后修改时间',
    constraint uk_tid_ref_oid unique (tag_id, ref_type, oid)
)
    comment '标签映射表';


---- ref_id         varchar(64)                               not null comment 'to be deleted',


CREATE TABLE IF NOT EXISTS tb_dictionary_type
(
    id             bigint unsigned auto_increment comment '主键'
        primary key,
    biz_id         varchar(64)                               not null comment '业务唯一标识(不可修改；业务键拼接并哈希)',
    version        int             default 0                 not null comment '数据版本号：用于幂等防并发',
    description    varchar(255)                              null comment '备注:介绍为啥添加这一条记录，这条记录干啥的，哪里用，怎么用',
    delete_version bigint unsigned default 0                 not null comment '删除标记：0-未删除；否则为删除时间',

    display_name   varchar(64)                               not null comment '名称',
    display_order  int                                       not null comment '顺序',
    source         varchar(64)                               not null comment '数据来源',

    creator        varchar(64)                               not null comment '创建人编号',
    create_time    datetime        default CURRENT_TIMESTAMP not null comment '创建时间',
    modifier       varchar(64)                               not null comment '最近修改人编码',
    update_time    datetime        default CURRENT_TIMESTAMP not null comment '最后修改时间'
)
    comment 'tb_dictionary_type';
-- H2 数据库中，索引名需要全局唯一，一般数据库的索引名只需要表内唯一即可
CREATE INDEX IF NOT EXISTS idx_dic_type_bizid on tb_dictionary_type (biz_id);
CREATE INDEX IF NOT EXISTS idx_dic_type_order on tb_dictionary_type (display_order);

CREATE TABLE IF NOT EXISTS tb_dictionary_item
(
    id              bigint unsigned auto_increment comment '主键'
        primary key,
    biz_id          varchar(64)                               not null comment '业务唯一标识(不可修改；业务键拼接并哈希)',

    version         int             default 0                 not null comment '数据版本号：用于幂等防并发',
    description     varchar(255)                              null comment '备注:介绍为啥添加这一条记录，这条记录干啥的，哪里用，怎么用',

    dictionary_type varchar(64)                               not null comment '字典类型编码',
    name            varchar(64)                               not null comment '名称',
    display_name    varchar(64)                               not null comment '展示名称',
    display_order   int                                       not null comment '顺序',
    parent_id       bigint                                    null comment '父节点id',

    delete_version  bigint unsigned default 0                 not null comment '删除标记：0-未删除；否则为删除时间',
    creator         varchar(64)                               not null comment '创建人编号',
    create_time     datetime        default CURRENT_TIMESTAMP not null comment '创建时间',
    modifier        varchar(64)                               not null comment '最近修改人编码',
    update_time     datetime        default CURRENT_TIMESTAMP not null comment '最后修改时间'
)
    comment 'tb_dictionary_item';

CREATE INDEX IF NOT EXISTS idx_bizid on tb_dictionary_item (biz_id);
CREATE INDEX IF NOT EXISTS idx_pid on tb_dictionary_item (parent_id);

-- CREATE TABLE IF NOT EXISTS dict_hierarchy
-- (
--    id            bigint unsigned auto_increment comment '主键',
--    ancestor_id   INT NOT NULL comment '数据版本号：用于幂等防并发',
--    descendant_id INT NOT NULL comment '数据版本号：用于幂等防并发',
--    depth         INT NOT NULL comment '深度差/距离',
--    PRIMARY KEY (ancestor_id, descendant_id)
--                                FOREIGN KEY (ancestor_id) REFERENCES dictionary(dict_id),
--                                FOREIGN KEY (descendant_id) REFERENCES dictionary(dict_id)
-- );

-- ------------------
CREATE TABLE IF NOT EXISTS system_lock
(
    resource     varchar(64)             not null comment '锁定的资源，组件标识:模块标识:资源/操作标识'
        primary key,
    owner        varchar(64)             not null comment '持有者，可通过该值解析持有应用 / 机器 / 线程 等',
    token        varchar(64)             not null comment '令牌，用于操作锁（获取、解锁、修改）在达到 ttl 之前，必须通过该令牌，才能对锁进行操作',
    version      int          default 0  not null comment '版本号',
    lock_time    datetime                not null comment '上锁时间',
    release_time datetime                not null comment '超时自动释放时间',
    description  varchar(128) default '' not null comment '备注：描述这个锁的目的'
)
    comment '全局锁';

-- shoulder-batch

CREATE TABLE IF NOT EXISTS batch_record
(
    id          varchar(48)                        not null comment '主键'
        primary key,
    data_type   varchar(64)                        not null comment '导入数据类型，建议可翻译。对应 导入数据库表名 / 领域对象名称，如用户、人员、订单',
    operation   varchar(64)                        null comment '业务操作类型，如校验、同步、导入、更新，可空',
    total_num   int                                not null comment '总数据数量',
    success_num int                                not null comment '执行成功条数',
    fail_num    int                                not null comment '执行失败条数',
    creator     varchar(64)                        not null comment '创建人编号',
    create_time datetime default CURRENT_TIMESTAMP null comment '创建时间'
)
    comment '批量任务执行记录';

CREATE TABLE IF NOT EXISTS batch_record_detail
(
    id          int auto_increment comment '主键'
        primary key,
    record_id   varchar(48)   not null comment '批量任务执行表id',
    index_no    int           not null comment '该任务中，本数据行对应的行号 / 下标值',
    operation   varchar(64)   not null comment '业务操作类型，如校验、同步、导入、更新',
    status      int           not null comment '结果 0 执行成功 1 执行失败、2 跳过',
    fail_reason varchar(1024) null comment '失败原因，推荐支持多语言',
    source      text          null comment '导入的原数据'
)
    comment '批量任务执行详情';


CREATE TABLE IF NOT EXISTS dispatch_platform
(
    id               int AUTO_INCREMENT COMMENT '主键' PRIMARY KEY,
    biz_type         varchar(48)  NOT NULL COMMENT '平台类型',
    display_name     varchar(48)  NOT NULL COMMENT 'name',
    url              varchar(256) NOT NULL COMMENT 'url',
    description      varchar(64)  NOT NULL COMMENT '平台特点说明',
    core_value       varchar(256) NOT NULL COMMENT '用户需求，对该平台内容的追求，平台侧重点',
    user_tag         varchar(256) NOT NULL COMMENT '用户群体特征',
    precautions      varchar(256) NULL COMMENT '发文宣传注意事项',
    frequency_period varchar(16)  NULL COMMENT '推广频率-周期，如天、周、月',
    suggest_times    int          NULL COMMENT '推广频率-每frequency_period内频次，每周2-3次可以用 2.5，每月一次则为1',
    care_hotspot     int          NULL COMMENT '该平台是否需要着重考虑热点事件，使用0-5的分数表示，如微博非常需要，用5表示，语雀基本不需要用0表示',
    need_vpn         int          NOT NULL COMMENT '是否需要vpn访问',
    area             varchar(32)  NOT NULL COMMENT '地区，境内境外'
)
    COMMENT = '推广平台';
