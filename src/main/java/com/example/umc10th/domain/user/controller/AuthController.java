package com.example.umc10th.domain.user.controller;

import com.example.umc10th.domain.user.dto.UserRequestDto;
import com.example.umc10th.domain.user.dto.UserResponseDto;
import com.example.umc10th.domain.user.service.UserService;
import com.example.umc10th.global.apipayload.code.GeneralSuccessCode;
import com.example.umc10th.global.apipayload.handler.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController implements AuthControllerDocs {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<UserResponseDto.SignUpResult>> signUp(
            @Valid @RequestBody UserRequestDto.SignUp request) {
        UserResponseDto.SignUpResult result = userService.signUp(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.onSuccess(GeneralSuccessCode.CREATED, result));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<UserResponseDto.LoginResult>> login(
            @Valid @RequestBody UserRequestDto.Login request) {
        return ResponseEntity.ok(ApiResponse.onSuccess(GeneralSuccessCode.OK, userService.login(request)));
    }
}
