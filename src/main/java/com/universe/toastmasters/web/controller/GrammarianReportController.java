package com.universe.toastmasters.web.controller;

import com.universe.toastmasters.pojo.ApiResponse;
import com.universe.toastmasters.service.GrammarianReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author Nick Liu
 * @date 2024/6/5
 */
@RestController("/grammarian/report")
@RequiredArgsConstructor
public class GrammarianReportController {

	private final GrammarianReportService reportService;

	@PostMapping("/upload")
	public ApiResponse<String> uploadReport(MultipartFile file) throws IOException {
		return ApiResponse.success(reportService.resolveUploadReport(file.getInputStream()));
	}
}
