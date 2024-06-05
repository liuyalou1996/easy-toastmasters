package com.universe.toastmasters.pojo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * <p>
 * 语法官汇报概览表
 * </p>
 *
 * @author Nick Liu
 * @since 2024-06-05
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_grammarian_report_overview")
public class GrammarianReportOverviewDO {

	/**
	 * 主键ID
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Long id;

	/**
	 * 报表编号
	 */
	private Long reportNo;

	/**
	 * 记录员
	 */
	private String reporter;

	/**
	 * 标题
	 */
	private String title;

	/**
	 * 今日一词
	 */
	private String wordOfDay;

	/**
	 * 今日一词使用次数
	 */
	private Integer wordOfDayCount;

	/**
	 * 创建时间
	 */
	private LocalDateTime createTime;
}
