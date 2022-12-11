package com.universe.toastmasters.service.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.CellExtra;
import com.alibaba.excel.read.metadata.holder.ReadRowHolder;
import com.universe.toastmasters.pojo.model.AhCounterDataModel;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * @author Nick Liu
 * @date 2022/8/28
 */
@Slf4j
public class AhCounterReportListener extends AnalysisEventListener<AhCounterDataModel> {

	private int dataRowBatchCount;

	private List<AhCounterDataModel> result;

	private Consumer<List<AhCounterDataModel>> dataRowConsumer;

	private Consumer<Map<Integer, String>> headRowConsumer;

	public AhCounterReportListener(int dataBatchCount, Consumer<List<AhCounterDataModel>> dataRowConsumer, Consumer<Map<Integer, String>> headRowConsumer) {
		this.dataRowBatchCount = dataBatchCount;
		this.dataRowConsumer = dataRowConsumer;
		this.headRowConsumer = headRowConsumer;
		this.result = new ArrayList<>();
	}

	@Override
	public void invoke(AhCounterDataModel data, AnalysisContext context) {
		result.add(data);
		if (result.size() >= dataRowBatchCount) {
			invokeCallback();
		}
	}

	@Override
	public void doAfterAllAnalysed(AnalysisContext context) {
		log.info("Sheet has been all read,total count is: {}", context.readSheetHolder().getApproximateTotalRowNumber());
		// 读取完所有行之后再次回调传输剩余的数据
		if (!result.isEmpty()) {
			invokeCallback();
		}
	}

	@Override
	public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
		ReadRowHolder readRowHolder = context.readRowHolder();
		int rowIndex = readRowHolder.getRowIndex();

		log.info("Begin to read sheet head row, current row index: {}", readRowHolder.getRowIndex());

		// 只解析第二行的数据
		if (rowIndex == 1 || rowIndex == 2) {
			headRowConsumer.accept(headMap);
		}
	}

	@Override
	public void extra(CellExtra extra, AnalysisContext context) {
		log.info("Reading extra data...");
	}

	private void invokeCallback() {
		dataRowConsumer.accept(result);
		result.clear();
	}
}
