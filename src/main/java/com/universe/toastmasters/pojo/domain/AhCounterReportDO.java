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
@TableName("t_ah_counter_report")
public class AhCounterReportDO {

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
	 * 哼哈官名称
	 */
	private String ahCounter;

	/**
	 * 哼哈官汇报解析结果，以Json格式保存
	 */
	private String resolvedReportJson;

	/**
	 * 哼哈词名称中英文映射
	 */
	private String ahWordsNameMapping;

	/**
	 * 哼哈词总使用次数
	 */
	private Integer totalUsed;

	/**
	 * 每位嘉宾哼哈词使用情况
	 */
	private String usedInfoPerGuest;

	/**
	 * 哼哈词及使用次数映射
	 */
	private String usedWordAndCount;

	/**
	 * 哼哈词使用频率最高前3，以JSON格式保存
	 */
	private String mostUsedWordTop3;

	/**
	 * 哼哈词使用嘉宾和次数，以JSON格式保存
	 */
	private String usedGuestAndCount;

	/**
	 * 哼哈词使用次数最多嘉宾前3，以JSON格式保存
	 */
	private String mostUsedGuestTop3;

	/**
	 * 哼哈词使用次数最少嘉宾前3，以JSON格式保存
	 */
	private String leastUsedGuestTop3;

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

	public String getUsedInfoPerGuest() {
		return usedInfoPerGuest;
	}

	public void setUsedInfoPerGuest(String usedInfoPerGuest) {
		this.usedInfoPerGuest = usedInfoPerGuest;
	}

	public String getUsedWordAndCount() {
		return usedWordAndCount;
	}

	public void setUsedWordAndCount(String usedWordAndCount) {
		this.usedWordAndCount = usedWordAndCount;
	}

	public String getMostUsedWordTop3() {
		return mostUsedWordTop3;
	}

	public void setMostUsedWordTop3(String mostUsedWordTop3) {
		this.mostUsedWordTop3 = mostUsedWordTop3;
	}

	public String getUsedGuestAndCount() {
		return usedGuestAndCount;
	}

	public void setUsedGuestAndCount(String usedGuestAndCount) {
		this.usedGuestAndCount = usedGuestAndCount;
	}

	public String getMostUsedGuestTop3() {
		return mostUsedGuestTop3;
	}

	public void setMostUsedGuestTop3(String mostUsedGuestTop3) {
		this.mostUsedGuestTop3 = mostUsedGuestTop3;
	}

	public String getLeastUsedGuestTop3() {
		return leastUsedGuestTop3;
	}

	public void setLeastUsedGuestTop3(String leastUsedGuestTop3) {
		this.leastUsedGuestTop3 = leastUsedGuestTop3;
	}

	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "AhCounterReportDO{" + "id=" + id + ", reportNo=" + reportNo + ", ahCounter=" + ahCounter + ", resolvedReportJson=" + resolvedReportJson + ", totalUsed=" + totalUsed + ", usedInfoPerGuest="
			+ usedInfoPerGuest + ", usedWordAndCount=" + usedWordAndCount + ", mostUsedWordTop3=" + mostUsedWordTop3 + ", usedGuestAndCount=" + usedGuestAndCount + ", mostUsedGuestTop3=" + mostUsedGuestTop3
			+ ", leastUsedGuestTop3=" + leastUsedGuestTop3 + ", createTime=" + createTime + "}";
	}
}
