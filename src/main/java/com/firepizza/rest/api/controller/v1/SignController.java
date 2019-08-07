package com.firepizza.rest.api.controller.v1;

import com.firepizza.rest.api.advice.exception.CEmailSigninFailedException;
import com.firepizza.rest.api.config.security.JwtTokenProvider;
import com.firepizza.rest.api.entity.User;
import com.firepizza.rest.api.model.response.CommonResult;
import com.firepizza.rest.api.model.response.SingleResult;
import com.firepizza.rest.api.repo.UserJpaRepo;
import com.firepizza.rest.api.service.ResponseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@Api(tags = {"1. Sign"})
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1")
public class SignController {

	private final UserJpaRepo userJpaRepo;
	private final JwtTokenProvider jwtTokenProvider;
	private final ResponseService responseService;
	private final PasswordEncoder passwordEncoder;

	/**
	 * 로그인 성공 시에는 결과로 jwt토큰을 발급
	 */
	@ApiOperation(value = "로그인", notes = "이메일 회원 로그인을 한다.")
	@GetMapping(value = "/signin")
	public SingleResult<String> signin(@ApiParam(value = "회원ID : 이메일", required = true) @RequestParam String id,
									   @ApiParam(value = "비밀번호", required = true) @RequestParam String password) {

		User user = userJpaRepo.findByUid(id).orElseThrow(CEmailSigninFailedException::new);
		if (!passwordEncoder.matches(password, user.getPassword()))
			throw new CEmailSigninFailedException();

		return responseService.getSingleResult(jwtTokenProvider.createToken(user.getUsername(), user.getRoles()));
	}

	/**
	 * 가입 시에는 패스워드 인코딩을 위해 passwordEncoder설정을 합니다. 기본 설정은 bcrypt encoding이 사용됩니다.
	 */
	@ApiOperation(value = "가입", notes = "회원가입을 한다.")
	@GetMapping(value = "/signup")
	public CommonResult signin(@ApiParam(value = "회원ID : 이메일", required = true) @RequestParam String id,
							   @ApiParam(value = "비밀번호", required = true) @RequestParam String password,
							   @ApiParam(value = "이름", required = true) @RequestParam String name) {

		userJpaRepo.save(
				User.builder()
					.uid(id)
					.password(passwordEncoder.encode(password))
					.name(name)
					.roles(Collections.singletonList("ROLE_USER"))
					.build()
		);
		return responseService.getSuccessResult();
	}
}