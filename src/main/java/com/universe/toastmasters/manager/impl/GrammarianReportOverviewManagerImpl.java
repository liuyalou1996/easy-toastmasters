package com.universe.toastmasters.manager.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.universe.toastmasters.constant.CommonConst;
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

	@Override
	public GrammarianReportOverviewDO getReportOverview(long reportNo) {
		Wrapper<GrammarianReportOverviewDO> wrapper = Wrappers.<GrammarianReportOverviewDO>lambdaQuery()
			.eq(GrammarianReportOverviewDO::getReportNo, reportNo)
			.last(CommonConst.LIMIT_SQL);
		return reportOverviewMapper.selectOne(wrapper);
	}
}
