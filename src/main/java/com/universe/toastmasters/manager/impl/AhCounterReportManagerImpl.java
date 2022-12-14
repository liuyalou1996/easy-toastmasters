package com.universe.toastmasters.manager.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.universe.toastmasters.constant.CommonConst;
import com.universe.toastmasters.manager.AhCounterReportManager;
import com.universe.toastmasters.mapper.AhCounterReportMapper;
import com.universe.toastmasters.pojo.domain.AhCounterReportDO;
import com.universe.toastmasters.pojo.dto.AhCounterDataDTO;
import com.universe.toastmasters.pojo.dto.AhCounterReportDTO;
import com.universe.toastmasters.util.FastJsonUtils;
import com.universe.toastmasters.util.SnowFlakeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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
		Map<String, AhCounterDataDTO> usedInfoPerGuest = calAhWordsUsedInfoPerGuest(dataDTOList);

		// 哼哈词及使用次数映射
		Map<String, Integer> usedWordAndCount = Arrays.stream(AhCounterDataDTO.class.getDeclaredFields())
			.filter(field -> !Arrays.asList("id", "role", "name").contains(field.getName()))
			.peek(field -> field.setAccessible(true))
			.collect(
				Collectors.toMap(Field::getName, field -> dataDTOList.stream()
					.flatMapToInt(ahCounterDataDTO -> IntStream.of((int) ReflectionUtils.getField(field, ahCounterDataDTO)))
					.sum())
			);

		// 使用频率最高哼哈词前5
		Map<String, Integer>  mostUsedWordTop5 = usedWordAndCount.entrySet().stream()
			.sorted(Entry.<String, Integer>comparingByValue().reversed())
			.limit(5)
			.collect(Collectors.toMap(Entry::getKey, Entry::getValue));

		// 哼哈词使用名字和总次数
		Map<String, Integer> usedGuestAndCount = dataDTOList.stream()
			.collect(Collectors.groupingBy(AhCounterDataDTO::getName, Collectors.summingInt(AhCounterDataDTO::getAhWordsUsedCount)));

		// 哼哈词使用次数最多前5的名字和使用次数
		Map<String, Integer> mostUsedGuestTop5 = usedGuestAndCount.entrySet().stream()
			.sorted(Entry.<String,Integer>comparingByValue().reversed())
			.limit(5)
			.collect(Collectors.toMap(Entry::getKey, Entry::getValue));

		// 哼哈词使用次数最少前3的名字和使用次数
		Map<String, Integer> leastUsedGuestTop3 = usedGuestAndCount.entrySet().stream()
			.sorted(Entry.comparingByValue())
			.limit(3)
			.collect(Collectors.toMap(Entry::getKey, Entry::getValue));

		// 入库并返回报告编号
		long reportNo = SnowFlakeUtils.nextId();
		AhCounterReportDO ahCounterReportDO = AhCounterReportDO.builder()
			.ahCounter(ahCounterReportDTO.getAhCounter())
			.reportNo(reportNo)
			.resolvedReportJson(FastJsonUtils.toJsonString(dataDTOList))
			.ahWordsNameMapping(FastJsonUtils.toJsonString(ahCounterReportDTO.getAhWordsNameMapping()))
			.totalUsed(totalUsed)
			.usedInfoPerGuest(FastJsonUtils.toJsonString(usedInfoPerGuest))
			.usedWordAndCount(FastJsonUtils.toJsonString(usedWordAndCount))
			.mostUsedWordTop5(FastJsonUtils.toJsonString(mostUsedWordTop5))
			.usedGuestAndCount(FastJsonUtils.toJsonString(usedGuestAndCount))
			.mostUsedGuestTop5(FastJsonUtils.toJsonString(mostUsedGuestTop5))
			.leastUsedGuestTop3(FastJsonUtils.toJsonString(leastUsedGuestTop3))
			.build();
		ahCounterReportMapper.insert(ahCounterReportDO);
		return reportNo;
	}

	/**
	 * 计算每个嘉宾哼哈词使用情况
	 * @param dataDTOList
	 * @return
	 */
	private Map<String, AhCounterDataDTO> calAhWordsUsedInfoPerGuest(List<AhCounterDataDTO> dataDTOList) {
		Map<String, List<AhCounterDataDTO>> usedInfoPerGuestMapping = dataDTOList.stream()
			.collect(Collectors.groupingBy(AhCounterDataDTO::getName, Collectors.toList()));

		Map<String, AhCounterDataDTO> usedInfoPerGuest = new HashMap<>();
		usedInfoPerGuestMapping.forEach((key, value) -> {
			AhCounterDataDTO ahCounterDataDTO = new AhCounterDataDTO();
			value.forEach(dataDTO -> {
				ahCounterDataDTO.setId(dataDTO.getId());
				ahCounterDataDTO.setRole(dataDTO.getRole());
				ahCounterDataDTO.setName(dataDTO.getName());
				ahCounterDataDTO.setWordOfEm(ahCounterDataDTO.getWordOfEm() + dataDTO.getWordOfEm());
				ahCounterDataDTO.setWordOfAh(ahCounterDataDTO.getWordOfAh() + dataDTO.getWordOfAh());
				ahCounterDataDTO.setWordOfEh(ahCounterDataDTO.getWordOfEh() + dataDTO.getWordOfEh());
				ahCounterDataDTO.setWordOfHao(ahCounterDataDTO.getWordOfHao() + dataDTO.getWordOfHao());
				ahCounterDataDTO.setWordOfDui(ahCounterDataDTO.getWordOfDui() + dataDTO.getWordOfDui());
				ahCounterDataDTO.setWordOfNa(ahCounterDataDTO.getWordOfNa() + dataDTO.getWordOfNa());
				ahCounterDataDTO.setWordOfThis(ahCounterDataDTO.getWordOfThis() + dataDTO.getWordOfThis());
				ahCounterDataDTO.setWordOfThat(ahCounterDataDTO.getWordOfThat() + dataDTO.getWordOfThat());
				ahCounterDataDTO.setWordOfThen(ahCounterDataDTO.getWordOfThen() + dataDTO.getWordOfThen());
				ahCounterDataDTO.setWordOfSo(ahCounterDataDTO.getWordOfSo() + dataDTO.getWordOfSo());
				ahCounterDataDTO.setWordOfUs(ahCounterDataDTO.getWordOfUs() + dataDTO.getWordOfUs());
				ahCounterDataDTO.setWordOfThatIs(ahCounterDataDTO.getWordOfThatIs() + dataDTO.getWordOfThatIs());
			});
			usedInfoPerGuest.put(ahCounterDataDTO.getName(), ahCounterDataDTO);
		});
		return usedInfoPerGuest;
	}

	@Override
	public AhCounterReportDO getAhCounterReport(long reportNo){
		Wrapper<AhCounterReportDO> wrapper = Wrappers.lambdaQuery(AhCounterReportDO.class)
			.eq(AhCounterReportDO::getReportNo, reportNo)
			.last(CommonConst.LIMIT_SQL);
		return ahCounterReportMapper.selectOne(wrapper);
	}



	public static void main(String[] args) {
		List<AhCounterDataDTO> dataDTOList = new ArrayList<>();
		AhCounterDataDTO ahCounterDataDTO1 = AhCounterDataDTO.builder().name("Nick").wordOfAh(2).wordOfEm(10).build();
		AhCounterDataDTO ahCounterDataDTO2 = AhCounterDataDTO.builder().name("菲菲").wordOfAh(1).wordOfEm(10).build();
		AhCounterDataDTO ahCounterDataDTO3 = AhCounterDataDTO.builder().name("Vicky").wordOfAh(5).wordOfEm(10).build();
		AhCounterDataDTO ahCounterDataDTO4 = AhCounterDataDTO.builder().name("Vicky").wordOfAh(4).wordOfEm(8).build();
		dataDTOList.add(ahCounterDataDTO1);
		dataDTOList.add(ahCounterDataDTO2);
		dataDTOList.add(ahCounterDataDTO3);
		dataDTOList.add(ahCounterDataDTO4);
	}
}
