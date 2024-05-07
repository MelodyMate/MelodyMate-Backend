package com.melodymatebackend.users.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    User findByNickname(String nickname);

    boolean existsByEmail(String email);

    boolean existsByNickname(String nickname);

    User findByUsername(String username);
}
