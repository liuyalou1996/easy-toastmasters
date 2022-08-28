package com.universe.toastmasters.pojo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;

/**
 * Ah-counter统计报告表
 *
 * @author Nick Liu
 * @since 2022-08-27
 */
@TableName("t_ahcounter_report")
public class AhcounterReportDO {

	/**
	 * 主键ID
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Long id;

	/**
	 * 报表编号
	 */
	private Long reportNo;

	/**
	 * 哼哈官
	 */
	private String ahCounter;

	/**
	 * 哼哈官汇报解析结果Json
	 */
	private String resolvedResultJson;

	/**
	 * 角色名
	 */
	private String role;

	/**
	 * 姓名
	 */
	private String name;

	/**
	 * 哼哈词总使用次数
	 */
	private Integer totalUsed;

	private String frequentlyUsedWordsTop3;

	private String mostUsedNameTop3;

	private String leastUsedNameTop3;

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

	public Long getReportNo() {
		return reportNo;
	}

	public void setReportNo(Long reportNo) {
		this.reportNo = reportNo;
	}

	public String getAhCounter() {
		return ahCounter;
	}

	public void setAhCounter(String ahCounter) {
		this.ahCounter = ahCounter;
	}

	public String getResolvedResultJson() {
		return resolvedResultJson;
	}

	public void setResolvedResultJson(String resolvedResultJson) {
		this.resolvedResultJson = resolvedResultJson;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getTotalUsed() {
		return totalUsed;
	}

	public void setTotalUsed(Integer totalUsed) {
		this.totalUsed = totalUsed;
	}

	public String getFrequentlyUsedWordsTop3() {
		return frequentlyUsedWordsTop3;
	}

	public void setFrequentlyUsedWordsTop3(String frequentlyUsedWordsTop3) {
		this.frequentlyUsedWordsTop3 = frequentlyUsedWordsTop3;
	}

	public String getMostUsedNameTop3() {
		return mostUsedNameTop3;
	}

	public void setMostUsedNameTop3(String mostUsedNameTop3) {
		this.mostUsedNameTop3 = mostUsedNameTop3;
	}

	public String getLeastUsedNameTop3() {
		return leastUsedNameTop3;
	}

	public void setLeastUsedNameTop3(String leastUsedNameTop3) {
		this.leastUsedNameTop3 = leastUsedNameTop3;
	}

	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "AhcounterReportDO{" + "id=" + id + ", reportNo=" + reportNo + ", ahCounter=" + ahCounter + ", resolvedResultJson="
			+ resolvedResultJson + ", role=" + role + ", name=" + name + ", totalUsed=" + totalUsed + ", frequentlyUsedWordsTop3="
			+ frequentlyUsedWordsTop3 + ", mostUsedNameTop3=" + mostUsedNameTop3 + ", leastUsedNameTop3=" + leastUsedNameTop3 + ", createTime="
			+ createTime + "}";
	}
}
