package com.firepizza.rest.api.config.security;

import com.firepizza.rest.api.advice.exception.CUserNotFoundException;
import com.firepizza.rest.api.repo.UserJpaRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {

	private final UserJpaRepo userJpaRepo;

	public UserDetails loadUserByUsername(String uid) {
		return userJpaRepo.findByUid(uid).orElseThrow(CUserNotFoundException::new);
	}

}