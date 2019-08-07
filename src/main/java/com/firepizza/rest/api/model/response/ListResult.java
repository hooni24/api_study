package com.firepizza.rest.api.model.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 결과가 여러건인 api를 담는 응답모델
 * @param <T>
 */
@Getter
@Setter
public class ListResult<T> extends CommonResult {

	private List<T> list;

}
