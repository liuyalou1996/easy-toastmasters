package com.universe.toastmasters.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

/**
 * @author Nick Liu
 * @date 2022/12/13
 */
@RestController
public class ShortLinkController {

	@GetMapping("/redirect")
	public RedirectView redirect() {
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl("https://www.baidu.com/");
		redirectView.setStatusCode(HttpStatus.FOUND);
		return redirectView;
	}
}
