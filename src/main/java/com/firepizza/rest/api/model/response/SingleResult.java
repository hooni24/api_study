package com.firepizza.rest.api.model.response;

import lombok.Getter;
import lombok.Setter;

/**
 * 결과가 단일건인 API를 담는 응답 모델
 * @param <T>
 */
@Getter
@Setter
public class SingleResult<T> extends CommonResult {

	private T data;

}
