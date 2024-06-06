package com.universe.toastmasters.manager.impl;

import com.universe.toastmasters.manager.GrammarianReportDetailManager;
import com.universe.toastmasters.mapper.GrammarianReportDetailMapper;
import com.universe.toastmasters.pojo.domain.GrammarianReportDetailDO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Nick Liu
 * @date 2024/6/5
 */

@Repository
@RequiredArgsConstructor
public class GrammarianReportDetailManagerImpl implements GrammarianReportDetailManager {

	private final GrammarianReportDetailMapper reportDetailMapper;

	@Override
	public void saveReportDetail(GrammarianReportDetailDO reportDetailDO) {
		reportDetailMapper.insert(reportDetailDO);
	}

	@Override
	public void batchSaveReportDetail(List<GrammarianReportDetailDO> reportDetailDOList) {
		reportDetailMapper.batchSaveReportDetail(reportDetailDOList);
	}
}
