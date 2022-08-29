package com.universe.toastmasters.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.universe.toastmasters.common.WebApplicationContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 刘亚楼
 * @date 2022/2/27
 */
@Slf4j
@Configuration
public class WebMvcConfig implements WebMvcConfigurer, ApplicationContextAware {

	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.removeIf(MappingJackson2HttpMessageConverter.class::isInstance);

		FastJsonHttpMessageConverter messageConverter = new FastJsonHttpMessageConverter();
		List<MediaType> mediaTypeList = new ArrayList<>();
		mediaTypeList.add(MediaType.APPLICATION_JSON);
		messageConverter.setSupportedMediaTypes(mediaTypeList);

		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		fastJsonConfig.setCharset(StandardCharsets.UTF_8);
		fastJsonConfig.setSerializerFeatures(SerializerFeature.WriteMapNullValue);
		messageConverter.setFastJsonConfig(fastJsonConfig);

		converters.add(messageConverter);
	}


	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		log.info("Loading web application context.");
		WebApplicationContextHolder.setWebApplicationContext(applicationContext);
	}
}
