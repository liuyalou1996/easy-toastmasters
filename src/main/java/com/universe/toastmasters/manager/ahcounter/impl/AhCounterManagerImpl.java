package com.universe.toastmasters.manager.ahcounter.impl;

import com.universe.toastmasters.manager.ahcounter.AhCounterManager;
import com.universe.toastmasters.mapper.AhcounterReportMapper;
import com.universe.toastmasters.pojo.dto.AhCounterDataDTO;
import com.universe.toastmasters.pojo.dto.AhCounterReportDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.ReflectionUtils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Nick Liu
 * @date 2022/8/28
 */
@Repository
public class AhCounterManagerImpl implements AhCounterManager {

	@Autowired
	private AhcounterReportMapper ahcounterReportMapper;

	@Override
	public void saveAhCounterReport(AhCounterReportDTO ahCounterReportDTO) {
		List<AhCounterDataDTO> dataDTOList = ahCounterReportDTO.getAhCounterDataDTOList();

		// 哼哈词总使用次数
		int totalUsed = dataDTOList.stream().flatMapToInt(ahCounterDataDTO -> IntStream.of(ahCounterDataDTO.getAhWordsUsedCount())).sum();

		// 使用频率最高哼哈词前3
		Map<String, Integer> mostUsedWordsTop3 = Arrays.stream(AhCounterDataDTO.class.getDeclaredFields())
			.filter(field -> !Arrays.asList("role", "name").contains(field.getName()))
			.peek(field -> field.setAccessible(true))
			.collect(
				Collectors.toMap(field -> field.getName(), field -> dataDTOList.stream()
					.flatMapToInt(ahCounterDataDTO -> IntStream.of((int) ReflectionUtils.getField(field, ahCounterDataDTO)))
					.sum())
			);

		// 哼哈词使用次数最多前3的名字和使用次数
		Map<String, Integer> mostUsedTop3 = dataDTOList.stream()
			.sorted(Comparator.comparing(AhCounterDataDTO::getAhWordsUsedCount).reversed())
			.limit(3)
			.collect(Collectors.groupingBy(AhCounterDataDTO::getName, LinkedHashMap::new, Collectors.summingInt(AhCounterDataDTO::getAhWordsUsedCount)));

		// 哼哈词使用次数最少前3的名字和使用次数
		Map<String, Integer> leastUsedTop3 = dataDTOList.stream()
			.sorted(Comparator.comparing(AhCounterDataDTO::getAhWordsUsedCount))
			.limit(3)
			.collect(Collectors.groupingBy(AhCounterDataDTO::getName, LinkedHashMap::new, Collectors.summingInt(AhCounterDataDTO::getAhWordsUsedCount)));

	}
}
