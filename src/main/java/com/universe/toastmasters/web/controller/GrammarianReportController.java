package com.universe.toastmasters.web.controller;

import com.universe.toastmasters.pojo.ApiResponse;
import com.universe.toastmasters.pojo.vo.GrammarianReportVO;
import com.universe.toastmasters.service.GrammarianReportService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * @author Nick Liu
 * @date 2024/6/5
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/grammarian/report")
public class GrammarianReportController {

	private static final String FILE_NAME = "语法官汇报模板.xlsx";

	private final GrammarianReportService reportService;

	@GetMapping("/template")
	public ResponseEntity<byte[]> downloadAhCounterReportTemplate() throws IOException {
		Resource template = new ClassPathResource(String.format("excel/%s", FILE_NAME));
		InputStream inputStream = template.getInputStream();
		byte[] body = IOUtils.toByteArray(inputStream);
		IOUtils.closeQuietly(inputStream);

		HttpHeaders respHeaders = new HttpHeaders();
		respHeaders.setContentDisposition(ContentDisposition.attachment().filename(FILE_NAME, StandardCharsets.UTF_8).build());
		respHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		return new ResponseEntity<>(body, respHeaders, HttpStatus.CREATED);
	}

	@PostMapping("/upload")
	public ApiResponse<String> uploadReport(MultipartFile file) throws IOException {
		return ApiResponse.success(reportService.resolveUploadReport(file.getInputStream()));
	}

	@GetMapping("/detail/{reportNo}")
	public ApiResponse<GrammarianReportVO> queryGrammarianReport(@PathVariable long reportNo) {
		return ApiResponse.success(reportService.queryGrammarianReport(reportNo));
	}
}
