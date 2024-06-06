# 哼哈官汇报表
create table t_ah_counter_report
(
    id                    bigint primary key auto_increment comment '主键ID',
    report_no             bigint        default 0                 not null comment '报表编号',
    ah_counter            varchar(16)   default ''                not null comment '哼哈官名称',
    resolved_report_json  mediumtext                              not null comment '哼哈官汇报解析结果，以Json格式保存',
    ah_words_name_mapping varchar(512)  default ''                not null comment '哼哈词名称中英文映射',
    total_used            smallint      default 0                 not null comment '哼哈词总使用次数',
    used_info_per_guest   mediumtext                              not null comment '每位嘉宾哼哈词使用情况',
    used_word_and_count   varchar(1024) default ''                not null comment '哼哈词及使用次数映射',
    most_used_word_top5   varchar(256)  default ''                not null comment '哼哈词使用频率最高前5，以JSON格式保存',
    used_guest_and_count  varchar(256)  default ''                not null comment '哼哈词使用嘉宾和次数，以JSON格式保存',
    most_used_guest_top5  varchar(256)  default ''                not null comment '哼哈词使用次数最多嘉宾前5，以JSON格式保存',
    least_used_guest_top3 varchar(256)  default ''                not null comment '哼哈词使用次数最少嘉宾前3，以JSON格式保存',
    create_time           datetime      default CURRENT_TIMESTAMP not null comment '创建时间'
) comment 'Ah-counter统计报告表';

create index idx_accounter_report_create_time on t_ah_counter_report (create_time);
create index idx_accounter_report_no on t_ah_counter_report (report_no);

# 短链信息表
create table `t_short_link`
(
    `id`             bigint primary key auto_increment comment '主键ID',
    `short_link`     varchar(32)  not null default '' comment '短链接',
    `long_link_hash` bigint       not null default 0 comment 'hash值',
    `long_link`      varchar(128) not null default '' comment '长链接',
    `status`         tinyint      not null default 1 comment '状态：1-可用，0-不可用',
    `expiry_time`    datetime     null comment '过期时间',
    `create_time`    datetime     not null default current_timestamp comment '创建时间'
) comment '短链信息表';
create index idx_sl_hash_long_link on t_short_link (long_link_hash, long_link);
create index idx_sl_short_link on t_short_link (short_link);

# 语法官汇报概览表
create table `t_grammarian_report_overview`
(
    `id`                bigint primary key auto_increment comment '主键ID',
    `report_no`         bigint      not null comment '报表编号',
    `reporter`          varchar(32) not null comment '记录员',
    `title`             varchar(64) not null comment '标题',
    `word_of_day`       varchar(32) not null comment '今日一词',
    `word_of_day_count` int         not null default 0 comment '今日一词使用次数',
    `create_time`       datetime    not null default current_timestamp comment '创建时间'
) comment '语法官汇报概览表';
create index `idx_tgro_report_no` on t_grammarian_report_overview(report_no);

# 语法官汇报详情表
create table `t_grammarian_report_detail`
(
    `id`             bigint primary key auto_increment comment '主键ID',
    `report_no`      bigint       not null comment '报表编号',
    `name`           varchar(32)  not null comment '姓名',
    `section`        varchar(16)  null comment '会议环节',
    `speech_figures` varchar(16)  null comment '修辞手法',
    `sentence`       varchar(255) null comment '金句',
    `create_time`    datetime  not null default current_timestamp comment '创建时间'
) comment '语法官汇报详情表';
create index `idx_tgrd_report_no` on t_grammarian_report_detail(report_no);
