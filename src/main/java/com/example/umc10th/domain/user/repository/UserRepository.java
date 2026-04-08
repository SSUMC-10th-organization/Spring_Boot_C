package com.example.umc10th.domain.user.repository;

import com.example.umc10th.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
