package com.universe.toastmasters.web.controller;

import com.universe.toastmasters.pojo.ApiResponse;
import com.universe.toastmasters.pojo.dto.AhCounterReportDTO;
import com.universe.toastmasters.pojo.vo.AhCounterReportDetailVO;
import com.universe.toastmasters.pojo.vo.AhCounterReportOverviewVO;
import com.universe.toastmasters.service.ahcounter.AhCounterService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @author 刘亚楼
 * @date 2022/8/27
 */
@Controller
@RequestMapping("/ah-counter/report")
public class AhCounterReportController {

	private static final String FILE_NAME = "哼哈官汇报模板.xlsx";

	@Autowired
	private AhCounterService ahCounterService;

	@GetMapping("/template")
	public ResponseEntity<byte[]> downloadAhCounterReportTemplate() throws IOException {
		Resource template = new ClassPathResource(String.format("excel/%s", FILE_NAME));
		byte[] body = FileUtils.readFileToByteArray(template.getFile());

		HttpHeaders respHeaders = new HttpHeaders();
		respHeaders.setContentDisposition(ContentDisposition.attachment().filename(FILE_NAME, StandardCharsets.UTF_8).build());
		respHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);

		return new ResponseEntity<>(body, respHeaders, HttpStatus.CREATED);
	}

	@ResponseBody
	@PostMapping("/upload")
	public ApiResponse<Long> resolveAhCounterReport(MultipartFile file) throws IOException {
		AhCounterReportDTO ahCounterReportDTO = ahCounterService.resolveAhCounterReport(file.getInputStream());
		return ApiResponse.success(ahCounterService.saveAhCounterReport(ahCounterReportDTO));
	}

	@ResponseBody
	@GetMapping("/ah-words-mapping/{reportNo}")
	public ApiResponse<Map<String, String>> queryAhWordsNameMapping(@PathVariable("reportNo") long reportNo) {
		return ApiResponse.success(ahCounterService.queryAhWordsNameMapping(reportNo));
	}

	@ResponseBody
	@GetMapping("/overview/{reportNo}")
	public ApiResponse<AhCounterReportOverviewVO> queryAhCounterReportOverview(@PathVariable("reportNo") long reportNo) {
		return ApiResponse.success(ahCounterService.queryReportOverview(reportNo));
	}

	@ResponseBody
	@GetMapping("/detail/{reportNo}")
	public ApiResponse<AhCounterReportDetailVO> queryAhCounterReportDetail(@PathVariable("reportNo") long reportNo) {
		return ApiResponse.success(ahCounterService.queryReportDetail(reportNo));
	}

	@GetMapping("/page/overview/{reportNo}")
	public ModelAndView turnToReportOverviewPage(@PathVariable String reportNo) {
		ModelAndView modelAndView = new ModelAndView("ah-counter/html/report-overview");
		modelAndView.addObject(reportNo);
		return modelAndView;
	}

	@GetMapping("page/detail/{reportNo}")
	public ModelAndView turnToReportDetailPage(@PathVariable String reportNo) {
		ModelAndView modelAndView = new ModelAndView("ah-counter/html/report-detail");
		modelAndView.addObject(reportNo);
		return modelAndView;
	}

}
