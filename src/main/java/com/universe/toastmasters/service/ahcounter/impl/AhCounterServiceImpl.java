package com.universe.toastmasters.service.ahcounter.impl;

import com.universe.toastmasters.pojo.domain.AhcounterReportDO;
import com.universe.toastmasters.pojo.dto.AhCounterDataDTO;
import com.universe.toastmasters.pojo.dto.AhCounterReportDTO;
import com.universe.toastmasters.pojo.model.AhCounterDataModel;
import com.universe.toastmasters.service.ahcounter.AhCounterService;
import com.universe.toastmasters.service.ahcounter.listener.AhCounterReportListener;
import com.universe.toastmasters.util.EasyExcelUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author 刘亚楼
 * @date 2022/8/27
 */
@Service
public class AhCounterServiceImpl implements AhCounterService {

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

	public static void main(String[] args) {
		List<AhCounterDataDTO> dataDTOList = new ArrayList<>();
		AhCounterDataDTO ahCounterDataDTO1 = AhCounterDataDTO.builder().name("Nick").wordOfAh(2).wordOfEm(10).build();
		AhCounterDataDTO ahCounterDataDTO2 = AhCounterDataDTO.builder().name("菲菲").wordOfAh(1).wordOfEm(10).build();
		AhCounterDataDTO ahCounterDataDTO3 = AhCounterDataDTO.builder().name("Vicky").wordOfAh(5).wordOfEm(10).build();
		dataDTOList.add(ahCounterDataDTO1);
		dataDTOList.add(ahCounterDataDTO2);
		dataDTOList.add(ahCounterDataDTO3);
	}

}
