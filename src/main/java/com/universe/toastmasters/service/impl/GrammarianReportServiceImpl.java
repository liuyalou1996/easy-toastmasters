package com.universe.toastmasters.service.impl;

import com.universe.toastmasters.manager.GrammarianReportDetailManager;
import com.universe.toastmasters.manager.GrammarianReportOverviewManager;
import com.universe.toastmasters.pojo.domain.GrammarianReportDetailDO;
import com.universe.toastmasters.pojo.domain.GrammarianReportOverviewDO;
import com.universe.toastmasters.pojo.model.GrammarianDataModel;
import com.universe.toastmasters.pojo.vo.GrammarianReportVO;
import com.universe.toastmasters.pojo.vo.GrammarianReportVO.GrammarianReportDetailVO;
import com.universe.toastmasters.service.GrammarianReportService;
import com.universe.toastmasters.service.listener.GrammarianReportListener;
import com.universe.toastmasters.util.EasyExcelUtils;
import com.universe.toastmasters.util.SnowFlakeUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class GrammarianReportServiceImpl implements GrammarianReportService {

	private final GrammarianReportOverviewManager reportOverviewManager;
	private final GrammarianReportDetailManager reportDetailManager;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public String resolveUploadReport(InputStream is) {
		List<String> headRowList = new ArrayList<>();
		Consumer<String> headRowConsumer = headRowList::add;
		List<GrammarianDataModel> dataModelList = new ArrayList<>();
		Consumer<List<GrammarianDataModel>> dataRowConsumer = dataModelList::addAll;

		// 异步读取Excel
		GrammarianReportListener listener = new GrammarianReportListener(dataRowConsumer, headRowConsumer);
		EasyExcelUtils.readSheetAsynchronously(is, GrammarianDataModel.class, listener, 1, 5);

		// 保存概览信息
		Long reportNo = SnowFlakeUtils.nextId();
		GrammarianReportOverviewDO reportOverviewDO = GrammarianReportOverviewDO.builder()
			.reportNo(reportNo)
			.title(headRowList.get(0))
			.reporter(headRowList.get(1))
			.wordOfDay(headRowList.get(2))
			.wordOfDayCount(Integer.parseInt(headRowList.get(3)))
			.build();
		reportOverviewManager.saveReportOverview(reportOverviewDO);

		// 保存详情信息
		List<GrammarianReportDetailDO> reportDetailDOList = dataModelList.stream().map(model -> {
			GrammarianReportDetailDO reportDetailDO = new GrammarianReportDetailDO();
			BeanUtils.copyProperties(model, reportDetailDO);
			reportDetailDO.setReportNo(reportNo);
			return reportDetailDO;
		}).collect(Collectors.toList());
		reportDetailManager.batchSaveReportDetail(reportDetailDOList);

		return String.valueOf(reportNo);
	}

	@Override
	public GrammarianReportVO queryGrammarianReport(long reportNo) {
		GrammarianReportOverviewDO reportOverviewDO = reportOverviewManager.getReportOverview(reportNo);
		if (reportOverviewDO == null) {
			return new GrammarianReportVO();
		}

		List<GrammarianReportDetailDO> reportDetailDOList = reportDetailManager.listReportDetails(reportNo);
		List<GrammarianReportDetailVO> detailVOList = reportDetailDOList.stream().map(reportDetailDO -> {
			GrammarianReportDetailVO reportDetailVO = new GrammarianReportDetailVO();
			BeanUtils.copyProperties(reportDetailDO, reportDetailVO);
			return reportDetailVO;
		}).collect(Collectors.toList());

		return GrammarianReportVO.builder()
			.reportNo(reportOverviewDO.getReportNo())
			.title(reportOverviewDO.getTitle())
			.reporter(reportOverviewDO.getReporter())
			.wordOfDay(reportOverviewDO.getWordOfDay())
			.wordOfDayCount(reportOverviewDO.getWordOfDayCount())
			.details(detailVOList)
			.build();
	}

}
