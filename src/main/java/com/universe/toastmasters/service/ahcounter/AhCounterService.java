package com.universe.toastmasters.service.ahcounter;

import com.universe.toastmasters.pojo.dto.AhCounterReportDTO;

import java.io.InputStream;

/**
 * @author 刘亚楼
 * @date 2022/8/27
 */
public interface AhCounterService {

	AhCounterReportDTO resolveAhCounterReport(InputStream is);
}
