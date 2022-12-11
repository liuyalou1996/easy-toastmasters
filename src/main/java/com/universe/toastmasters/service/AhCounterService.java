package com.universe.toastmasters.service;

import com.universe.toastmasters.pojo.dto.AhCounterReportDTO;
import com.universe.toastmasters.pojo.vo.AhCounterReportDetailVO;
import com.universe.toastmasters.pojo.vo.AhCounterReportOverviewVO;

import java.io.InputStream;
import java.util.Map;

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

	/**
	 * 查询指定报告编号哼哈词中英文映射
	 * @param reportNo
	 * @return
	 */
	Map<String, String> queryAhWordsNameMapping(long reportNo);

	/**
	 * 查询报告概要
	 * @param reportNo
	 * @return
	 */
	AhCounterReportOverviewVO queryReportOverview(long reportNo);

	/**
	 * 查询报告详情
	 * @param reportNo
	 * @return
	 */
	AhCounterReportDetailVO queryReportDetail(long reportNo);
}
