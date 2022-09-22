package com.universe.toastmasters.web.controller;

import com.universe.toastmasters.pojo.dto.QrCodeGenerationDTO;
import com.universe.toastmasters.util.QrCodeUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
		byte[] data = QrCodeUtils.generateGreenQrCode(content, width, height);

		HttpHeaders respHeaders = new HttpHeaders();
		respHeaders.setContentType(MediaType.IMAGE_PNG);
		return new ResponseEntity<>(data, respHeaders, HttpStatus.CREATED);
	}

}
