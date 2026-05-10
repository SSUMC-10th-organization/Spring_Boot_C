package com.example.umc10th.domain.mission.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class MissionRequestDto {

    public record Create() {
    }

    public record GetMyMissions(
            @NotNull Long userId,
            @Min(0) int page
    ) {
    }
}
