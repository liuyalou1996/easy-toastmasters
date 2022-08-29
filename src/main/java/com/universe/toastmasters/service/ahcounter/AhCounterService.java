package com.universe.toastmasters.service.ahcounter;

import com.universe.toastmasters.pojo.dto.AhCounterReportDTO;

import java.io.InputStream;

/**
 * @author 刘亚楼
 * @date 2022/8/27
 */
public interface AhCounterService {

	/**
	 * 解析哼哈官汇报Excel模板内容
	 * @param is
	 * @return
	 */
	AhCounterReportDTO resolveAhCounterReport(InputStream is);

	/**
	 * 保存哼哈官报告结果
	 * @param ahCounterReportDTO
	 * @return
	 */
	Long saveAhCounterReport(AhCounterReportDTO ahCounterReportDTO);
}
