package com.universe.toastmasters.manager.ahcounter;

import com.universe.toastmasters.pojo.dto.AhCounterReportDTO;

/**
 * @author Nick Liu
 * @date 2022/8/28
 */
public interface AhCounterReportManager {

	long saveAhCounterReport(AhCounterReportDTO ahCounterReportDTO);
}
