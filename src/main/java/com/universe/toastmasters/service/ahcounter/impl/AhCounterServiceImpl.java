package com.universe.toastmasters.service.ahcounter.impl;

import com.universe.toastmasters.manager.ahcounter.AhCounterReportManager;
import com.universe.toastmasters.pojo.domain.AhCounterReportDO;
import com.universe.toastmasters.pojo.dto.AhCounterDataDTO;
import com.universe.toastmasters.pojo.dto.AhCounterReportDTO;
import com.universe.toastmasters.pojo.model.AhCounterDataModel;
import com.universe.toastmasters.pojo.vo.AhCounterReportOverviewVO;
import com.universe.toastmasters.service.ahcounter.AhCounterService;
import com.universe.toastmasters.service.ahcounter.listener.AhCounterReportListener;
import com.universe.toastmasters.util.EasyExcelUtils;
import com.universe.toastmasters.util.FastJsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * @author 刘亚楼
 * @date 2022/8/27
 */
@Service
public class AhCounterServiceImpl implements AhCounterService {

	@Autowired
	private AhCounterReportManager ahCounterReportManager;

	@Override
	public AhCounterReportDTO resolveAhCounterReport(InputStream is) {
		List<AhCounterDataModel> dataModelList = new ArrayList<>();
		Consumer<List<AhCounterDataModel>> dataRowConsumer = dataModelList::addAll;

		Map<Integer, String> headRowMap = new HashMap<>();
		Consumer<Map<Integer, String>> headRowConsumer = headRowMap::putAll;

		AhCounterReportListener listener = new AhCounterReportListener(100, dataRowConsumer, headRowConsumer);
		// 异步读取Excel
		EasyExcelUtils.readSheetAsynchronously(is, AhCounterDataModel.class, listener, 1, 3);

		List<AhCounterDataDTO> ahcounterReportDTOList = dataModelList.stream().map(dataModel -> {
			AhCounterDataDTO ahCounterDataDTO = AhCounterDataDTO.builder()
				.role(dataModel.getRole())
				.name(dataModel.getName())
				.wordOfEm(parseIntOrElseZeroIfBlank(dataModel.getWordOfEm()))
				.wordOfAh(parseIntOrElseZeroIfBlank(dataModel.getWordOfAh()))
				.wordOfEh(parseIntOrElseZeroIfBlank(dataModel.getWordOfEh()))
				.wordOfHao(parseIntOrElseZeroIfBlank(dataModel.getWordOfHao()))
				.wordOfDui(parseIntOrElseZeroIfBlank(dataModel.getWordOfDui()))
				.wordOfNa(parseIntOrElseZeroIfBlank(dataModel.getWordOfNa()))
				.wordOfThis(parseIntOrElseZeroIfBlank(dataModel.getWordOfThis()))
				.wordOfThat(parseIntOrElseZeroIfBlank(dataModel.getWordOfThat()))
				.wordOfThen(parseIntOrElseZeroIfBlank(dataModel.getWordOfThen()))
				.wordOfSo(parseIntOrElseZeroIfBlank(dataModel.getWordOfSo()))
				.wordOfUs(parseIntOrElseZeroIfBlank(dataModel.getWordOfUs()))
				.wordOfThatIs(parseIntOrElseZeroIfBlank(dataModel.getWordOfThatIs()))
				.build();
			return ahCounterDataDTO;
		}).collect(Collectors.toList());

		String ahCounter = headRowMap.values()
			.stream()
			.findFirst()
			.map(content -> StringUtils.trim(content.split(":")[1]))
			.orElse(StringUtils.EMPTY);

		return AhCounterReportDTO.builder()
			.ahCounter(ahCounter)
			.ahCounterDataDTOList(ahcounterReportDTOList)
			.build();
	}

	private Integer parseIntOrElseZeroIfBlank(String str) {
		return Optional.ofNullable(str)
			.filter(StringUtils::isNotBlank)
			.map(Integer::parseInt)
			.orElse(0);
	}

	@Override
	public Long saveAhCounterReport(AhCounterReportDTO ahCounterReportDTO){
		return ahCounterReportManager.saveAhCounterReport(ahCounterReportDTO);
	}

	@Override
	public AhCounterReportOverviewVO queryReportOverview(long reportNo) {
		AhCounterReportDO ahCounterReportDO = ahCounterReportManager.getAhCounterReport(reportNo);
		return AhCounterReportOverviewVO.builder()
			.reportNo(reportNo)
			.ahCounter(ahCounterReportDO.getAhCounter())
			.totalUsed(ahCounterReportDO.getTotalUsed())
			.usedWordAndCount(FastJsonUtils.toIntegerValMap(ahCounterReportDO.getUsedWordAndCount()))
			.usedGuestAndCount(FastJsonUtils.toIntegerValMap(ahCounterReportDO.getUsedGuestAndCount()))
			.mostUsedWordTop3(FastJsonUtils.toIntegerValMap(ahCounterReportDO.getMostUsedWordTop3()))
			.mostUsedGuestTop3(FastJsonUtils.toIntegerValMap(ahCounterReportDO.getMostUsedGuestTop3()))
			.leastUsedGuestTop3(FastJsonUtils.toIntegerValMap(ahCounterReportDO.getLeastUsedGuestTop3()))
			.build();
	}

}
