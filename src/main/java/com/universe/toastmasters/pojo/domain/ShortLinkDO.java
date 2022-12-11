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
 * @since 2022-12-11
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
	private String hash;

	/**
	 * 长链接
	 */
	private String longLink;

	/**
	 * 创建时间
	 */
	private LocalDateTime createTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getShortLink() {
		return shortLink;
	}

	public void setShortLink(String shortLink) {
		this.shortLink = shortLink;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getLongLink() {
		return longLink;
	}

	public void setLongLink(String longLink) {
		this.longLink = longLink;
	}

	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "ShortLinkDO{" + "id=" + id + ", shortLink=" + shortLink + ", hash=" + hash + ", longLink=" + longLink + ", createTime=" + createTime + "}";
	}
}
