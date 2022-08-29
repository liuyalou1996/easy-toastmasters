package com.universe.toastmasters.pojo;

import com.universe.toastmasters.constant.ErrorCode;
import com.universe.toastmasters.util.MessageUtils;
import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.i18n.LocaleContextHolder;

import java.io.Serializable;
import java.util.Collections;

/**
 * @author 刘亚楼
 * @date 2022/3/12
 */
@Data
@Builder
public class ApiResponse<T> implements Serializable {

	private static final long serialVersionUID = -171570687030205212L;

	private boolean success;

	private T data;

	private String code;

	private String msg;

	public static <T> ApiResponse<T> success() {
		return success((T) Collections.emptyMap());
	}

	public static <T> ApiResponse<T> success(T data) {
		return ApiResponse.<T>builder()
			.success(true)
			.data(data)
			.code(ErrorCode.SUCCESS)
			.msg(StringUtils.EMPTY)
			.build();
	}

	public static <T> ApiResponse<T> error() {
		return error(ErrorCode.SYSTEM_ERROR, StringUtils.EMPTY);
	}

	public static <T> ApiResponse<T> error(String code) {
		return error(code, new Object[] {});
	}

	public static <T> ApiResponse<T> error(String code, Object[] args) {
		String msg = MessageUtils.getMessage(code, args, LocaleContextHolder.getLocale());
		return error(code, msg);
	}


	public static <T> ApiResponse<T> error(String code, String msg) {
		return ApiResponse.<T>builder().success(false).code(code).msg(msg).build();
	}

}
