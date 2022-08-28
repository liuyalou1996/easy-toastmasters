package com.universe.toastmasters.pojo.model;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 刘亚楼
 * @date 2022/8/27
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AhCounterDataModel {

	/**
	 * 角色名称
	 */
	@ExcelProperty(index = 0, value = "角色")
	private String role;

	/**
	 * 姓名
	 */
	@ExcelProperty(index = 1, value = "姓名")
	private String name;

	/**
	 * 哼哈词：嗯
	 */
	@ExcelProperty(index = 2, value = "嗯")
	private String wordOfEm;

	/**
	 * 哼哈词：啊
	 */
	@ExcelProperty(index = 3, value = "啊")
	private String wordOfAh;

	/**
	 * 哼哈词：额
	 */
	@ExcelProperty(index = 4, value = "呃")
	private String wordOfEh;

	/**
	 * 哼哈词：好
	 */
	@ExcelProperty(index = 5, value = "好")
	private String wordOfHao;

	/**
	 * 哼哈词：对
	 */
	@ExcelProperty(index = 6, value = "对")
	private String wordOfDui;

	/**
	 * 哼哈词：那
	 */
	@ExcelProperty(index = 7, value = "那")
	private String wordOfNa;

	/**
	 * 哼哈词：这个
	 */
	@ExcelProperty(index = 8, value = "这个")
	private String wordOfThis;

	/**
	 * 哼哈词：那个
	 */
	@ExcelProperty(index = 9, value = "那个")
	private String wordOfThat;

	/**
	 * 哼哈词：然后
	 */
	@ExcelProperty(index = 10, value = "然后")
	private String wordOfThen;

	/**
	 * 哼哈词：那么
	 */
	@ExcelProperty(index = 11, value = "那么")
	private String wordOfSo;

	/**
	 * 哼哈词：我们
	 */
	@ExcelProperty(index = 12, value = "我们")
	private String wordOfUs;

	/**
	 * 哼哈词：就是说
	 */
	@ExcelProperty(index = 13, value = "就是说")
	private String wordOfThatIs;

}
