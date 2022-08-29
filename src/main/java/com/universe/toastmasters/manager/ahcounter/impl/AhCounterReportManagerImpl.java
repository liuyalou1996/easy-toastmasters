package com.universe.toastmasters.manager.ahcounter.impl;

import com.universe.toastmasters.manager.ahcounter.AhCounterReportManager;
import com.universe.toastmasters.mapper.AhCounterReportMapper;
import com.universe.toastmasters.pojo.domain.AhCounterReportDO;
import com.universe.toastmasters.pojo.dto.AhCounterDataDTO;
import com.universe.toastmasters.pojo.dto.AhCounterReportDTO;
import com.universe.toastmasters.util.FastJsonUtils;
import com.universe.toastmasters.util.SnowFlakeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.ReflectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Nick Liu
 * @date 2022/8/28
 */
@Repository
public class AhCounterReportManagerImpl implements AhCounterReportManager {

	@Autowired
	private AhCounterReportMapper ahCounterReportMapper;

	@Override
	public long saveAhCounterReport(AhCounterReportDTO ahCounterReportDTO) {
		List<AhCounterDataDTO> dataDTOList = ahCounterReportDTO.getAhCounterDataDTOList();

		// 哼哈词总使用次数
		int totalUsed = dataDTOList.stream().flatMapToInt(ahCounterDataDTO -> IntStream.of(ahCounterDataDTO.getAhWordsUsedCount())).sum();

		// 会议角色哼哈词使用情况
		Map<String, List<AhCounterDataDTO>> usedInfoPerGuest = dataDTOList.stream()
			.collect(Collectors.groupingBy(AhCounterDataDTO::getName, LinkedHashMap::new, Collectors.toList()));

		// 哼哈词及使用次数映射
		Map<String, Integer> usedWordAndCount = Arrays.stream(AhCounterDataDTO.class.getDeclaredFields())
			.filter(field -> !Arrays.asList("role", "name").contains(field.getName()))
			.peek(field -> field.setAccessible(true))
			.collect(
				Collectors.toMap(field -> field.getName(), field -> dataDTOList.stream()
					.flatMapToInt(ahCounterDataDTO -> IntStream.of((int) ReflectionUtils.getField(field, ahCounterDataDTO)))
					.sum())
			);

		// 使用频率最高哼哈词前3
		List<Entry<String, Integer>> mostUsedWordTop3 = usedWordAndCount.entrySet()
			.stream()
			.sorted(Entry.<String, Integer>comparingByValue().reversed())
			.limit(3)
			.collect(Collectors.toList());

		// 哼哈词使用名字和总次数
		Map<String, Integer> usedGuestAndCount = dataDTOList.stream()
			.collect(Collectors.groupingBy(AhCounterDataDTO::getName, LinkedHashMap::new, Collectors.summingInt(AhCounterDataDTO::getAhWordsUsedCount)));

		// 哼哈词使用次数最多前3的名字和使用次数
		Map<String, Integer> mostUsedGuestTop3 = dataDTOList.stream()
			.sorted(Comparator.comparing(AhCounterDataDTO::getAhWordsUsedCount).reversed())
			.limit(3)
			.collect(Collectors.groupingBy(AhCounterDataDTO::getName, LinkedHashMap::new, Collectors.summingInt(AhCounterDataDTO::getAhWordsUsedCount)));

		// 哼哈词使用次数最少前3的名字和使用次数
		Map<String, Integer> leastUsedGuestTop3 = dataDTOList.stream()
			.sorted(Comparator.comparing(AhCounterDataDTO::getAhWordsUsedCount))
			.limit(3)
			.collect(Collectors.groupingBy(AhCounterDataDTO::getName, LinkedHashMap::new, Collectors.summingInt(AhCounterDataDTO::getAhWordsUsedCount)));

		// 入库并返回报告编号
		long reportNo = SnowFlakeUtils.nextId();
		AhCounterReportDO ahCounterReportDO = AhCounterReportDO.builder()
			.ahCounter(ahCounterReportDTO.getAhCounter())
			.reportNo(reportNo)
			.resolvedReportJson(FastJsonUtils.toJsonString(dataDTOList))
			.totalUsed(totalUsed)
			.usedInfoPerGuest(FastJsonUtils.toJsonString(usedInfoPerGuest))
			.usedWordAndCount(FastJsonUtils.toJsonString(usedWordAndCount))
			.mostUsedWordTop3(FastJsonUtils.toJsonString(mostUsedWordTop3))
			.usedGuestAndCount(FastJsonUtils.toJsonString(usedGuestAndCount))
			.mostUsedGuestTop3(FastJsonUtils.toJsonString(mostUsedGuestTop3))
			.leastUsedGuestTop3(FastJsonUtils.toJsonString(leastUsedGuestTop3))
			.build();
		ahCounterReportMapper.insert(ahCounterReportDO);

		return reportNo;
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
