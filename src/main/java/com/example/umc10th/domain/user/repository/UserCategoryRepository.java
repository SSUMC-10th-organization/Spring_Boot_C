package com.example.umc10th.domain.user.repository;

import com.example.umc10th.domain.user.entity.UserCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserCategoryRepository extends JpaRepository<UserCategory, Long> {
}
