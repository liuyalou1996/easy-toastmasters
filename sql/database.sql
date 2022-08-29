# 哼哈官汇报表
create table t_ahcounter_report
(
    id                   bigint primary key auto_increment comment '主键ID',
    report_no            bigint       not null default 0 comment '报表编号',
    ah_counter           varchar(16)  not null default '' comment '哼哈官',
    resolved_report_json varchar(512) not null default '' comment '汇报解析结果，以Json格式保存',
    total_used           smallint     not null default 0 comment '哼哈词总使用次数',
    most_used_word_top3  varchar(128) not null default '' comment '哼哈词使用频率最高前3，以Json格式保存',
    most_used_name_top3  varchar(128) not null default '' comment '哼哈词使用次数最多姓名前3，以Json格式保存',
    least_used_name_top3 varchar(128) not null default '' comment '哼哈词使用次数最少姓名前3，以Json格式保存',
    create_time          datetime     not null default CURRENT_TIMESTAMP comment '创建时间'
) comment 'Ah-counter统计报告表';

create index idx_accounter_report_create_time on t_ahcounter_report (create_time);
create index idx_accounter_report_no on t_ahcounter_report (report_no);
