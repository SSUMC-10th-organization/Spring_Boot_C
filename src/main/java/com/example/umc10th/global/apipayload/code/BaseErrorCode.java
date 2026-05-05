package com.example.umc10th.global.apipayload.code;

import com.example.umc10th.global.apipayload.ApiPayload;
import org.springframework.http.HttpStatus;

public interface BaseErrorCode extends ApiPayload {
    HttpStatus getStatus();
}
