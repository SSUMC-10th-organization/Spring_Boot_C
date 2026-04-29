package com.example.umc10th.domain.mission.exception;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import com.example.umc10th.global.apiPayload.exception.GeneralException;

public class MissionException extends GeneralException {

    public MissionException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}