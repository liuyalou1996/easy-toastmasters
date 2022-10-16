package com.universe.toastmasters.pojo.dto;

import lombok.Data;

/**
 * @author 刘亚楼
 * @date 2022/9/22
 */
@Data
public class QrCodeGenerationDTO {

	/**
	 * 二维码显示内容
	 */
	private String content;

	/**
	 * 二维码图片宽度
	 */
	private int width;

	/**
	 * 二维码图片高度
	 */
	private int height;

	/**
	 * 二维码图片Logo的大小：L-large,M-middle,S-small
	 */
	private String logoSize;
}
