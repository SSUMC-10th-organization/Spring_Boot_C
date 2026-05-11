package com.example.umc10th.domain.mission.dto;

import lombok.Getter;

public class MissionRequestDTO {

    @Getter
    public static class MissionSuccessDTO {
        private String proofType;
        private String proofImage;
        private String memo;
    }

    // 7주차 미션 1번을 위해 추가된 코드 (Request Body로 userId 받기)
    @Getter
    public static class GetInprogressMissionReq {
        private Long userId;
    }
}
