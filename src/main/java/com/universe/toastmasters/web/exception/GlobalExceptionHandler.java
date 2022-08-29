package com.universe.toastmasters.web.exception;

import com.universe.toastmasters.constant.ErrorCode;
import com.universe.toastmasters.pojo.ApiResponse;
import com.universe.toastmasters.util.MessageUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author 刘亚楼
 * @date 2020/10/27
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	@ResponseBody
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ApiResponse<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		FieldError fieldError = e.getBindingResult().getFieldError();
		// 校验注解名称
		String code = fieldError.getCode();
		// 校验字段名称
		String field = fieldError.getField();
		// 检验失败默认消息
		String defaultMessage = fieldError.getDefaultMessage();

		List<Object> args = Arrays.stream(Optional.ofNullable(fieldError.getArguments()).orElse(new Object[] {}))
			.filter(argument -> !(argument instanceof DefaultMessageSourceResolvable)).map(Object::toString).collect(Collectors.toList());
		args.add(0, field);

		// 默认根据注解名称取，如果没有则取默认消息
		String errorMsg = MessageUtils.getMessage(code, args.toArray(), LocaleContextHolder.getLocale(), StringUtils.EMPTY);
		if (StringUtils.isNotBlank(errorMsg)) {
			return ApiResponse.error(ErrorCode.INVALID_PARAM, errorMsg);
		}

		return ApiResponse.error(ErrorCode.INVALID_PARAM, defaultMessage);
	}


	@ResponseBody
	@ExceptionHandler(NullPointerException.class)
	public ApiResponse<Void> handleNullPointerException() {
		return ApiResponse.error(ErrorCode.SYSTEM_ERROR);
	}

	@ResponseBody
	@ExceptionHandler(Exception.class)
	public ApiResponse<Void> handleException() {
		return ApiResponse.error(ErrorCode.SYSTEM_ERROR);
	}

}
