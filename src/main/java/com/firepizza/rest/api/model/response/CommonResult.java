package com.firepizza.rest.api.model.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * API의 실행 결과를 담는 공통 응답 모델
 */
@Getter
@Setter
public class CommonResult {

	@ApiModelProperty(value = "응답 성공여부 : true/false")
	private boolean success;

	@ApiModelProperty(value = "응답 코드 번호 : >= 0 정상, < 0 비정상")
	private int code;

	@ApiModelProperty(value = "응답 메시지")
	private String msg;

}
