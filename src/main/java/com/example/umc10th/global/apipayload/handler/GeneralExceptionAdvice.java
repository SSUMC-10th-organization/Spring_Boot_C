package com.example.umc10th.global.apipayload.handler;

import com.example.umc10th.global.apipayload.code.BaseErrorCode;
import com.example.umc10th.global.apipayload.code.GeneralErrorCode;
import com.example.umc10th.global.apipayload.exception.GeneralException;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GeneralExceptionAdvice {

    // 1. @Valid 검증 실패 먼저 처리 (RequestBody)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleValidationException(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        BaseErrorCode code = GeneralErrorCode.VALID_FAIL;

        return ResponseEntity.status(code.getStatus())
                .body(ApiResponse.onFailure(code, errors));
    }

    // 2. @PathVariable, @RequestParam 등의 유효성 검증 실패 처리 (추가된 부분), page 체크 실패 포함
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponse<Object>> handleConstraintViolationException(ConstraintViolationException ex) {

        String errorMessage = ex.getConstraintViolations().stream()
                .map(violation -> violation.getMessage())
                .findFirst()
                .orElse("유효성 검사 실패");

        BaseErrorCode code = GeneralErrorCode.VALID_FAIL;

        return ResponseEntity.status(code.getStatus())
                .body(ApiResponse.onFailure(code, errorMessage));
    }

    // 3. 비즈니스 예외 처리
    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<ApiResponse<Object>> handleGeneralException(GeneralException ex) {

        BaseErrorCode errorCode = ex.getErrorCode();

        return ResponseEntity.status(errorCode.getStatus())
                .body(ApiResponse.onFailure(errorCode, null));
    }

    // 4. 가장 마지막에 모든 예기치 못한 예외 처리
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleException(Exception ex) {

        log.error("Unhandled exception occurred", ex);

        BaseErrorCode code = GeneralErrorCode.INTERNAL_SERVER_ERROR;

        return ResponseEntity.status(code.getStatus())
                .body(ApiResponse.onFailure(code, null));
    }
}
