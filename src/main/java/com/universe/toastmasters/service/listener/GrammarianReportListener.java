package com.universe.toastmasters.service.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.read.metadata.holder.ReadRowHolder;
import com.universe.toastmasters.pojo.model.GrammarianDataModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * @author Nick Liu
 * @date 2024/6/5
 */
@Slf4j
public class GrammarianReportListener extends AnalysisEventListener<GrammarianDataModel> {

	private List<GrammarianDataModel> result = new ArrayList<>();
	private Consumer<List<GrammarianDataModel>> dataRowConsumer;
	private Consumer<String> headRowConsumer;

	public GrammarianReportListener(Consumer<List<GrammarianDataModel>> dataRowConsumer, Consumer<String> headRowConsumer) {
		this.dataRowConsumer = dataRowConsumer;
		this.headRowConsumer = headRowConsumer;
	}

	@Override
	public void invoke(GrammarianDataModel data, AnalysisContext context) {
		result.add(data);
	}

	@Override
	public void doAfterAllAnalysed(AnalysisContext context) {
		log.info("语法官汇报数据已经全部解析完成");
		dataRowConsumer.accept(result);
	}

	@Override
	public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
		ReadRowHolder readRowHolder = context.readRowHolder();
		int rowIndex = readRowHolder.getRowIndex();
		if (rowIndex == 0) {
			log.info("识别到语法官报表数据标题:{}", headMap);
			String title = headMap.values().stream().findFirst().orElse(StringUtils.EMPTY);
			headRowConsumer.accept(title);
		} else if (rowIndex > 0 && rowIndex < 4) {
			log.info("识别到语法官报表数据概览信息,行数:{},数据:{}", rowIndex, headMap);
			String value = headMap.values().stream().findFirst().map(v -> v.split(":")[1]).orElse(StringUtils.EMPTY);
			headRowConsumer.accept(StringUtils.trim(value));
		}
	}
}
