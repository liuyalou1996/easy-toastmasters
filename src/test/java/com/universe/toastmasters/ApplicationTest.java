package com.universe.toastmasters;

import com.universe.toastmasters.service.ShortLinkService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = Application.class)
public class ApplicationTest {

	@Autowired
	private ShortLinkService shortLinkService;

	@Test
	public void generateShortLinkTest() {
		String shortLink = shortLinkService.generateShortLink("https://www.baidu.com/");
		System.err.println("生成的短链为：" + shortLink);
	}
}
