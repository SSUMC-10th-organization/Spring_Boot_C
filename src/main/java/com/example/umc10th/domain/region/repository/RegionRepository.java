package com.example.umc10th.domain.region.repository;

import com.example.umc10th.domain.region.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepository extends JpaRepository<Region, Long> {
}
