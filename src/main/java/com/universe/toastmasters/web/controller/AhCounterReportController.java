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
	public ApiResponse<Long> resolveAhCounterReport(MultipartFile multipartFile) throws IOException {
		AhCounterReportDTO ahCounterReportDTO = ahCounterService.resolveAhCounterReport(multipartFile.getInputStream());
		ahCounterService.saveAhCounterReport(ahCounterReportDTO);
		return ApiResponse.success();
	}

	@GetMapping("/overview/{reportNo}")
	public ModelAndView reportOverview(@PathVariable("reportNo") long reportNo) {
		ModelAndView modelAndView = new ModelAndView("ahcounter-report");
		return modelAndView;
	}

}
