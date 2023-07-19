package com.universe.toastmasters.web.controller;

import com.universe.toastmasters.manager.common.HttpClientManager;
import com.universe.toastmasters.manager.common.HttpClientManager.HttpClientResp;
import com.universe.toastmasters.util.FastJsonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author Nick Liu
 * @date 2023/7/17
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class WechatDevController {

	public static final String HOST = "http://112.74.179.144:12138/";

	private final HttpClientManager httpClientManager;

	@PostMapping("/kiakiaxi/**")
	public Map<String, Object> forwardRequest(@RequestBody Map<String, Object> map, HttpServletRequest request) throws Exception {
		String requestURL = request.getRequestURL().toString();
		String moduleName = requestURL.split("/kiakiaxi/")[1];
		String url = HOST + moduleName;

		log.info("Request URL: {}, request content:{}", url, FastJsonUtils.toJsonString(map));
		HttpClientResp httpClientResp = httpClientManager.postInJson(url, null, FastJsonUtils.toJsonString(map));
		log.info("Request URL: {}, response: {}", url, httpClientResp.getRespContent());
		return FastJsonUtils.toMap(httpClientResp.getRespContent());
	}
}
