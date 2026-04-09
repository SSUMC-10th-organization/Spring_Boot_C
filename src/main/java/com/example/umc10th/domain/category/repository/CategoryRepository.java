package com.example.umc10th.domain.category.repository;

import com.example.umc10th.domain.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
