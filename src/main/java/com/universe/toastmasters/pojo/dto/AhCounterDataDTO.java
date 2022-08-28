package com.universe.toastmasters.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Nick Liu
 * @date 2022/8/28
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AhCounterDataDTO {

	/**
	 * 角色名称
	 */
	private String role;

	/**
	 * 姓名
	 */
	private String name;

	/**
	 * 哼哈词：嗯
	 */
	private Integer wordOfEm;

	/**
	 * 哼哈词：啊
	 */
	private Integer wordOfAh;

	/**
	 * 哼哈词：额
	 */
	private Integer wordOfEh;

	/**
	 * 哼哈词：好
	 */
	private Integer wordOfHao;

	/**
	 * 哼哈词：对
	 */
	private Integer wordOfDui;

	/**
	 * 哼哈词：那
	 */
	private Integer wordOfNa;

	/**
	 * 哼哈词：这个
	 */
	private Integer wordOfThis;

	/**
	 * 哼哈词：那个
	 */
	private Integer wordOfThat;

	/**
	 * 哼哈词：然后
	 */
	private Integer wordOfThen;

	/**
	 * 哼哈词：那么
	 */
	private Integer wordOfSo;

	/**
	 * 哼哈词：我们
	 */
	private Integer wordOfUs;

	/**
	 * 哼哈词：就是说
	 */
	private Integer wordOfThatIs;
}
