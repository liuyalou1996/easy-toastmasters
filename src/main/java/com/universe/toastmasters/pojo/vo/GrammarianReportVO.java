package com.universe.toastmasters.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * @author Nick Liu
 * @date 2024/6/6
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GrammarianReportVO {

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

	private Map<String, List<GrammarianReportDetailVO>> reportDetailMap;

	@Data
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	public static class GrammarianReportDetailVO {

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
		private List<String> sentences;
	}
}
