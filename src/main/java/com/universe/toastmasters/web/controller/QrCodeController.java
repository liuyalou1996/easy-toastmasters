package com.universe.toastmasters.web.controller;

import com.universe.toastmasters.constant.enumeration.LogoSizeEnum;
import com.universe.toastmasters.pojo.dto.QrCodeGenerationDTO;
import com.universe.toastmasters.util.QrCodeUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.ByteArrayInputStream;

/**
 * @author 刘亚楼
 * @date 2022/9/22
 */
@Controller
@RequestMapping("/qrcode")
public class QrCodeController {

	@GetMapping("/generate")
	public ResponseEntity<byte[]> generateQrCode(QrCodeGenerationDTO qrCodeGenerationDTO) throws Exception {
		int width = qrCodeGenerationDTO.getWidth();
		int height = qrCodeGenerationDTO.getHeight();
		String content = qrCodeGenerationDTO.getContent();
		String logoName = LogoSizeEnum.fromLogoSize(qrCodeGenerationDTO.getLogoSize()).getLogoName();
		byte[] qrCodeData = QrCodeUtils.generateGreenQrCodeAsByteArray(content, width, height);

		ByteArrayInputStream bais = new ByteArrayInputStream(qrCodeData);
		Resource logoResource = new ClassPathResource("templates/images/".concat(logoName));
		byte[] respData = QrCodeUtils.attachLogoInTheMiddle(bais, logoResource.getInputStream());

		HttpHeaders respHeaders = new HttpHeaders();
		respHeaders.setContentType(MediaType.IMAGE_PNG);
		return new ResponseEntity<>(respData, respHeaders, HttpStatus.CREATED);
	}

}
