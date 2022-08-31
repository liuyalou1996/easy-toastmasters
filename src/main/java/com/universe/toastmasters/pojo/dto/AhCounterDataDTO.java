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
	 * 唯一ID
	 */
	private long id;

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
	private int wordOfEm;

	/**
	 * 哼哈词：啊
	 */
	private int wordOfAh;

	/**
	 * 哼哈词：额
	 */
	private int wordOfEh;

	/**
	 * 哼哈词：好
	 */
	private int wordOfHao;

	/**
	 * 哼哈词：对
	 */
	private int wordOfDui;

	/**
	 * 哼哈词：那
	 */
	private int wordOfNa;

	/**
	 * 哼哈词：这个
	 */
	private int wordOfThis;

	/**
	 * 哼哈词：那个
	 */
	private int wordOfThat;

	/**
	 * 哼哈词：然后
	 */
	private int wordOfThen;

	/**
	 * 哼哈词：那么
	 */
	private int wordOfSo;

	/**
	 * 哼哈词：我们
	 */
	private int wordOfUs;

	/**
	 * 哼哈词：就是说
	 */
	private int wordOfThatIs;

	/**
	 * 获取哼哈词使用次数
	 * @return
	 */
	public int getAhWordsUsedCount() {
		return this.wordOfEm + this.wordOfAh + this.wordOfEh + this.wordOfHao + this.wordOfDui + this.wordOfNa + this.wordOfThis + this.wordOfThat + this.wordOfThen + this.wordOfSo + this.wordOfUs
			+ this.wordOfThatIs;
	}
}
