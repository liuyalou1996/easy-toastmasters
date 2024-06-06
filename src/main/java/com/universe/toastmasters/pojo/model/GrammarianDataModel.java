package com.universe.toastmasters.pojo.model;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Nick Liu
 * @date 2024/6/5
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GrammarianDataModel {

	/**
	 * 姓名
	 */
	@ExcelProperty(index = 0, value = "姓名")
	private String name;

	/**
	 * 环节
	 */
	@ExcelProperty(index = 1, value = "环节")
	private String section;

	/**
	 * 比喻手法
	 */
	@ExcelProperty(index = 2, value = "比喻手法")
	private String speechFigures;

	/**
	 * 金句
	 */
	@ExcelProperty(index = 3, value = "金句")
	private String sentence;

}
