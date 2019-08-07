package com.firepizza.rest.api.repo;

import com.firepizza.rest.api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepo extends JpaRepository<User, Integer> {

	// 회원 가입시 등록한 이메일 조회를 위해
	Optional<User> findByUid(String email);

}
