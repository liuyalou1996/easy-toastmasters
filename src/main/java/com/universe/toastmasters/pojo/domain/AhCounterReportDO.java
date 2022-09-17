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
 * Ah-counter统计报告表
 *
 * @author Nick Liu
 * @since 2022-08-29
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_ah_counter_report")
public class AhCounterReportDO {

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
	 * 哼哈官名称
	 */
	private String ahCounter;

	/**
	 * 哼哈官汇报解析结果，以Json格式保存
	 */
	private String resolvedReportJson;

	/**
	 * 哼哈词名称中英文映射
	 */
	private String ahWordsNameMapping;

	/**
	 * 哼哈词总使用次数
	 */
	private Integer totalUsed;

	/**
	 * 每位嘉宾哼哈词使用情况
	 */
	private String usedInfoPerGuest;

	/**
	 * 哼哈词及使用次数映射
	 */
	private String usedWordAndCount;

	/**
	 * 哼哈词使用频率最高前5，以JSON格式保存
	 */
	private String mostUsedWordTop5;

	/**
	 * 哼哈词使用嘉宾和次数，以JSON格式保存
	 */
	private String usedGuestAndCount;

	/**
	 * 哼哈词使用次数最多嘉宾前5，以JSON格式保存
	 */
	private String mostUsedGuestTop5;

	/**
	 * 哼哈词使用次数最少嘉宾前3，以JSON格式保存
	 */
	private String leastUsedGuestTop3;

	/**
	 * 创建时间
	 */
	private LocalDateTime createTime;

}
