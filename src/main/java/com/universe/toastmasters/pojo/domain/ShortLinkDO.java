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
 * 短链信息表
 * </p>
 *
 * @author Nick Liu
 * @since 2022-12-13
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_short_link")
public class ShortLinkDO {

	/**
	 * 主键ID
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Long id;

	/**
	 * 短链接
	 */
	private String shortLink;

	/**
	 * hash值
	 */
	private Long longLinkHash;

	/**
	 * 长链接
	 */
	private String longLink;

	/**
	 * 状态：1-可用，0-不可用
	 */
	private boolean status;

	/**
	 * 过期时间
	 */
	private LocalDateTime expiryTime;

	/**
	 * 创建时间
	 */
	private LocalDateTime createTime;

}
