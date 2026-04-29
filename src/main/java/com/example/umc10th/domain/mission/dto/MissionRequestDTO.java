package com.example.umc10th.domain.mission.dto;

import lombok.Getter;

public class MissionRequestDTO {

    @Getter
    public static class MissionSuccessDTO {
        private String proofType;
        private String proofImage;
        private String memo;
    }
}
