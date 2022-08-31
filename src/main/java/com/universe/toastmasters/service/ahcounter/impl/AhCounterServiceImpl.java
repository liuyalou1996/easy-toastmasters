package com.universe.toastmasters.service.ahcounter.impl;

import com.alibaba.excel.annotation.ExcelProperty;
import com.universe.toastmasters.manager.ahcounter.AhCounterReportManager;
import com.universe.toastmasters.pojo.domain.AhCounterReportDO;
import com.universe.toastmasters.pojo.dto.AhCounterDataDTO;
import com.universe.toastmasters.pojo.dto.AhCounterReportDTO;
import com.universe.toastmasters.pojo.model.AhCounterDataModel;
import com.universe.toastmasters.pojo.vo.AhCounterReportDetailVO;
import com.universe.toastmasters.pojo.vo.AhCounterReportOverviewVO;
import com.universe.toastmasters.service.ahcounter.AhCounterService;
import com.universe.toastmasters.service.ahcounter.listener.AhCounterReportListener;
import com.universe.toastmasters.util.EasyExcelUtils;
import com.universe.toastmasters.util.FastJsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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

		List<Map<Integer, String>> headRowList = new ArrayList<>();
		Consumer<Map<Integer, String>> headRowConsumer = headRowList::add;

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

		// 哼哈官名字
		String ahCounter = headRowList.get(0).values()
			.stream()
			.findFirst()
			.map(content -> StringUtils.trim(content.split(":")[1]))
			.orElse(StringUtils.EMPTY);

		// 哼哈词中英文名映射
		Map<Integer, String> indexAndColMap = headRowList.get(1);
		Map<String, String> ahWordsNameMapping = Arrays.stream(AhCounterDataModel.class.getDeclaredFields())
			.collect(
				Collectors.toMap(Field::getName, field -> indexAndColMap.get(field.getAnnotation(ExcelProperty.class).index()))
			);

		return AhCounterReportDTO.builder()
			.ahCounter(ahCounter)
			.ahWordsNameMapping(ahWordsNameMapping)
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
	public Map<String,String> queryAhWordsNameMapping(long reportNo){
		return Optional.ofNullable(ahCounterReportManager.getAhCounterReport(reportNo))
			.map(AhCounterReportDO::getAhWordsNameMapping)
			.map(FastJsonUtils::toStringValMap)
			.orElse(Collections.emptyMap());
	}

	@Override
	public AhCounterReportOverviewVO queryReportOverview(long reportNo) {
		AhCounterReportDO ahCounterReportDO = ahCounterReportManager.getAhCounterReport(reportNo);
		if (ahCounterReportDO == null) {
			return AhCounterReportOverviewVO.builder().build();
		}


		return AhCounterReportOverviewVO.builder()
			.id(ahCounterReportDO.getId())
			.reportNo(String.valueOf(reportNo))
			.ahCounter(ahCounterReportDO.getAhCounter())
			.totalUsed(ahCounterReportDO.getTotalUsed())
			.ahWordsNameMapping(FastJsonUtils.toStringValMap(ahCounterReportDO.getAhWordsNameMapping()))
			.usedWordAndCount(FastJsonUtils.toIntegerValMap(ahCounterReportDO.getUsedWordAndCount()))
			.usedGuestAndCount(FastJsonUtils.toIntegerValMap(ahCounterReportDO.getUsedGuestAndCount()))
			.mostUsedWordTop3(FastJsonUtils.toIntegerValMap(ahCounterReportDO.getMostUsedWordTop3()))
			.mostUsedGuestTop3(FastJsonUtils.toIntegerValMap(ahCounterReportDO.getMostUsedGuestTop3()))
			.leastUsedGuestTop3(FastJsonUtils.toIntegerValMap(ahCounterReportDO.getLeastUsedGuestTop3()))
			.build();
	}

	@Override
	public AhCounterReportDetailVO queryReportDetail(long reportNo) {
		AhCounterReportDO ahCounterReportDO = ahCounterReportManager.getAhCounterReport(reportNo);
		if (ahCounterReportDO == null) {
			return new AhCounterReportDetailVO();
		}

		return FastJsonUtils.toJavaBean(ahCounterReportDO.getUsedInfoPerGuest(), AhCounterReportDetailVO.class);
	}





}
