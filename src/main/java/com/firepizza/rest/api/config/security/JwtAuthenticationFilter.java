package com.firepizza.rest.api.config.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * jwt가 유효한 토큰인지 인증하기 위한 filter
 * 이 필터는 Security 설정시 UsernamePasswordAuthenticationFilter앞에 세팅할 것입니다.
 */
public class JwtAuthenticationFilter extends GenericFilterBean {

	private JwtTokenProvider jwtTokenProvider;

	// jwt provider 주입
	public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider) {
		this.jwtTokenProvider = jwtTokenProvider;
	}

	// Request로 들어오는 Jwt token의 유효성을 검증(jwtTokenProvider.validateToken)하는 filter를 filterChanin에 연결합니다.
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		String token = jwtTokenProvider.resolveToken((HttpServletRequest) request);
		if (token != null && jwtTokenProvider.validateToken(token)) {
			Authentication auth = jwtTokenProvider.getAuthentication(token);
			SecurityContextHolder.getContext().setAuthentication(auth);
		}
		chain.doFilter(request, response);
	}
}
