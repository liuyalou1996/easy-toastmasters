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
 * 语法官汇报详情表
 * </p>
 *
 * @author Nick Liu
 * @since 2024-06-05
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_grammarian_report_detail")
public class GrammarianReportDetailDO {

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
	 * 姓名
	 */
	private String name;

	/**
	 * 会议环节
	 */
	private String section;

	/**
	 * 修辞手法
	 */
	private String speechFigures;

	/**
	 * 金句
	 */
	private String sentence;

	/**
	 * 创建时间
	 */
	private LocalDateTime createTime;
}
