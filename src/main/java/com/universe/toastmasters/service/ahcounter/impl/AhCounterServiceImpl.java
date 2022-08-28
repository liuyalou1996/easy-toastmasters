package com.universe.toastmasters.service.ahcounter.impl;

import com.universe.toastmasters.pojo.model.AhCounterDataModel;
import com.universe.toastmasters.service.ahcounter.AhCounterService;
import com.universe.toastmasters.service.ahcounter.listener.AhCounterReportListener;
import com.universe.toastmasters.util.EasyExcelUtils;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * @author 刘亚楼
 * @date 2022/8/27
 */
@Service
public class AhCounterServiceImpl implements AhCounterService {

	public AhCounterDataModel resolveAhCounterReport(InputStream is) {
		List<AhCounterDataModel> dataModelList = new ArrayList<>();
		Consumer<List<AhCounterDataModel>> dataRowConsumer = dataModelList::addAll;

		Map<Integer, String> headRowMap = new HashMap<>();
		Consumer<Map<Integer, String>> headRowConsumer = headRowMap::putAll;

		AhCounterReportListener listener = new AhCounterReportListener(50, dataRowConsumer, headRowConsumer);

		EasyExcelUtils.readSheetAsynchronously(is, AhCounterDataModel.class, listener, 1, 3);

		return null;
	}

}
