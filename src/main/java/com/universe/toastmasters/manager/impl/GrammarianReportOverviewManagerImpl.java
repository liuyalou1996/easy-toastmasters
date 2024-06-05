package com.universe.toastmasters.manager.impl;

import com.universe.toastmasters.manager.GrammarianReportOverviewManager;
import com.universe.toastmasters.mapper.GrammarianReportOverviewMapper;
import com.universe.toastmasters.pojo.domain.GrammarianReportOverviewDO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * @author Nick Liu
 * @date 2024/6/5
 */
@Repository
@RequiredArgsConstructor
public class GrammarianReportOverviewManagerImpl implements GrammarianReportOverviewManager {

	private final GrammarianReportOverviewMapper reportOverviewMapper;

	@Override
	public void saveReportOverview(GrammarianReportOverviewDO reportOverviewDO) {
		reportOverviewMapper.insert(reportOverviewDO);
	}
}
