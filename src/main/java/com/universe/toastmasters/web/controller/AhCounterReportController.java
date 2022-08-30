package com.universe.toastmasters.web.controller;

import com.universe.toastmasters.pojo.ApiResponse;
import com.universe.toastmasters.pojo.dto.AhCounterReportDTO;
import com.universe.toastmasters.service.ahcounter.AhCounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

/**
 * @author 刘亚楼
 * @date 2022/8/27
 */
@Controller
@RequestMapping("/ah-counter/report")
public class AhCounterReportController {

	@Autowired
	private AhCounterService ahCounterService;

	@ResponseBody
	@PostMapping("/upload")
	public ApiResponse<Long> resolveAhCounterReport(MultipartFile file) throws IOException {
		AhCounterReportDTO ahCounterReportDTO = ahCounterService.resolveAhCounterReport(file.getInputStream());
		ahCounterService.saveAhCounterReport(ahCounterReportDTO);
		return ApiResponse.success();
	}

	@ResponseBody
	@GetMapping("/overview/{reportNo}")
	public ApiResponse<Void> queryAhCounterReportOverview(@PathVariable("reportNo") String reportNo) {
		return ApiResponse.success();
	}

	@GetMapping("/overview")
	public ModelAndView reportOverview() {
		ModelAndView modelAndView = new ModelAndView("ahcounter-report");
		return modelAndView;
	}

}
