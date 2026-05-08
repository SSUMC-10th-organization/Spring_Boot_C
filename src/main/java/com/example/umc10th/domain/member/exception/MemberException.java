package com.example.umc10th.domain.member.exception;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import com.example.umc10th.global.exception.GeneralException;

public class MemberException extends GeneralException {
    public MemberException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
