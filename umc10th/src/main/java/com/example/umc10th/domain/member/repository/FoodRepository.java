package com.example.umc10th.domain.member.repository;

import com.example.umc10th.domain.member.entity.Food;
import com.example.umc10th.domain.member.enums.FoodName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FoodRepository extends JpaRepository<Food, Long> {
    Optional<Food> findByName(FoodName name);
}
