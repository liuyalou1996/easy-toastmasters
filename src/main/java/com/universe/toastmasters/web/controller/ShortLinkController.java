package com.universe.toastmasters.web.controller;

import com.universe.toastmasters.pojo.ApiResponse;
import com.universe.toastmasters.pojo.dto.ShortLinkGenerationDTO;
import com.universe.toastmasters.service.ShortLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

/**
 * @author Nick Liu
 * @date 2022/12/13
 */
@RestController
@RequestMapping("/shortLink")
public class ShortLinkController {

	@Autowired
	private ShortLinkService shortLinkService;

	@PostMapping("/generate")
	public ApiResponse<String> generateShortLink(@RequestBody ShortLinkGenerationDTO shortLinkGenerationDTO) {
		return ApiResponse.success(shortLinkService.generateShortLink(shortLinkGenerationDTO.getLongLink()));
	}

	@GetMapping("/redirect")
	public RedirectView redirect() {
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl("https://www.baidu.com/");
		redirectView.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
		return redirectView;
	}
}
