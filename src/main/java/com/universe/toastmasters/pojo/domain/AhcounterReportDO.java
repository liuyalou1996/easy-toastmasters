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
 * Ah-counter统计报告表
 *
 * @author Nick Liu
 * @since 2022-08-29
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
	 * 汇报解析结果，以Json格式保存
	 */
	private String resolvedReportJson;

	/**
	 * 哼哈词总使用次数
	 */
	private Integer totalUsed;

	/**
	 * 哼哈词使用频率最高前3，以Json格式保存
	 */
	private String mostUsedWordTop3;

	/**
	 * 哼哈词使用次数最多姓名前3，以Json格式保存
	 */
	private String mostUsedNameTop3;

	/**
	 * 哼哈词使用次数最少姓名前3，以Json格式保存
	 */
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

	public String getResolvedReportJson() {
		return resolvedReportJson;
	}

	public void setResolvedReportJson(String resolvedReportJson) {
		this.resolvedReportJson = resolvedReportJson;
	}

	public Integer getTotalUsed() {
		return totalUsed;
	}

	public void setTotalUsed(Integer totalUsed) {
		this.totalUsed = totalUsed;
	}

	public String getMostUsedWordTop3() {
		return mostUsedWordTop3;
	}

	public void setMostUsedWordTop3(String mostUsedWordTop3) {
		this.mostUsedWordTop3 = mostUsedWordTop3;
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
		return "AhcounterReportDO{" + "id=" + id + ", reportNo=" + reportNo + ", ahCounter=" + ahCounter + ", resolvedReportJson=" + resolvedReportJson + ", totalUsed=" + totalUsed + ", mostUsedWordTop3="
			+ mostUsedWordTop3 + ", mostUsedNameTop3=" + mostUsedNameTop3 + ", leastUsedNameTop3=" + leastUsedNameTop3 + ", createTime=" + createTime + "}";
	}
}
