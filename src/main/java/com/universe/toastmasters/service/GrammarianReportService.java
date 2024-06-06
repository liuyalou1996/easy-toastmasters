package com.universe.toastmasters.service;

import com.universe.toastmasters.pojo.vo.GrammarianReportVO;

import java.io.InputStream;

/**
 * @author Nick Liu
 * @date 2024/6/5
 */
public interface GrammarianReportService {

	String resolveUploadReport(InputStream is);

	GrammarianReportVO queryGrammarianReport(long reportNo);
}
