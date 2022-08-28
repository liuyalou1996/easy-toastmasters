package com.universe.toastmasters.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author 刘亚楼
 * @date 2022/8/27
 */
@Controller
@RequestMapping("/ah-counter")
public class AhCounterController {

	@RequestMapping("/report/resolve")
	public ModelAndView resolveAhCounterReport(MultipartFile multipartFile) {
		ModelAndView modelAndView = new ModelAndView();
		return modelAndView;
	}
}
