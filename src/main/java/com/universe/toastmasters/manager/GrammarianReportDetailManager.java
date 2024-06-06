package com.universe.toastmasters.manager;

import com.universe.toastmasters.pojo.domain.GrammarianReportDetailDO;

import java.util.List;

/**
 * @author Nick Liu
 * @date 2024/6/5
 */
public interface GrammarianReportDetailManager {

	void saveReportDetail(GrammarianReportDetailDO reportDetailDO);

	void batchSaveReportDetail(List<GrammarianReportDetailDO> reportDetailDOList);

	List<GrammarianReportDetailDO> listReportDetails(long reportNo);
}
