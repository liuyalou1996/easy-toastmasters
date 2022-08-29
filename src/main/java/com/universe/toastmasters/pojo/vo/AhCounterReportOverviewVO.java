package com.universe.toastmasters.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @author 刘亚楼
 * @date 2022/8/29
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AhCounterReportOverviewVO {

	/**
	 * 主键ID
	 */
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
	 * 总使用次数
	 */
	private int totalUsed;

	/**
	 * 哼哈词及使用次数映射
	 */
	private Map<String, Integer> usedWordAndCount;

	/**
	 * 哼哈词使用频率最高前3
	 */
	private Map<String, Integer> mostUsedWordTop3;

	/**
	 * 哼哈词使用嘉宾和次数
	 */
	private Map<String, Integer> usedGuestAndCount;

	/**
	 * 哼哈词使用次数最多嘉宾前3
	 */
	private Map<String, Integer> mostUsedGuestTop3;

	/**
	 * 哼哈词使用次数最少嘉宾前3
	 */
	private Map<String, Integer> leastUsedGuestTop3;

}
