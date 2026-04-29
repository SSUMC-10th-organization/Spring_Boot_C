package com.example.umc10th.domain.user.dto;

import com.example.umc10th.domain.mission.enums.MissionStatus;
import com.example.umc10th.domain.user.enums.Gender;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class UserResponseDto {

    public record Result() {
    }

    public record SignUpResult(
            Long userId,
            String name,
            Gender gender,
            LocalDate birthDate,
            List<String> foodPreferences,
            LocalDateTime createdAt
    ) {
    }

    public record CompleteMissionResult(
            Long missionId,
            String storeName,
            String reward,
            MissionStatus status,
            LocalDateTime completedAt
    ) {
    }
}
