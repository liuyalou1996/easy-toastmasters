package com.universe.toastmasters.constant.enumeration;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

/**
 * @author Nick Liu
 * @date 2022/10/16
 */
public enum LogoSizeEnum {

	/**
	 * 大图片尺寸
	 */
	LARGE("L", "wonderful_life_large.jpg"),
	/**
	 * 中图片尺寸
	 */
	MIDDLE("M", "wonderful_life_middle.jpg"),
	/**
	 * 小图片尺寸
	 */
	SMALL("S", "wonderful_life_small.jpg");

	private String logoSize;
	private String logoName;

	LogoSizeEnum(String logoSize, String logoName) {
		this.logoSize = logoSize;
		this.logoName = logoName;
	}

	public String getLogoSize() {
		return logoSize;
	}

	public String getLogoName() {
		return logoName;
	}

	public static LogoSizeEnum fromLogoSize(String logoSize) {
		return Arrays.stream(LogoSizeEnum.values())
			.filter(logoSizeEnum -> StringUtils.equals(logoSizeEnum.getLogoSize(), logoSize))
			.findAny()
			.orElse(null);
	}

}
