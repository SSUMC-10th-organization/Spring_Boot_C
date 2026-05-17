package com.example.umc10th.domain.user.dto;

import com.example.umc10th.domain.user.enums.Address;
import com.example.umc10th.domain.user.enums.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;

public class UserRequestDto {

    public record Create() {
    }

    public record SignUp(
            @NotBlank @Email String email,
            @NotBlank @Size(min = 8, message = "비밀번호는 8자 이상이어야 합니다.") String password,
            @NotBlank String name,
            @NotNull Gender gender,
            @NotNull LocalDate birthDate,
            Address address,
            @NotEmpty List<String> termsAgreed,
            List<String> foodPreferences
    ) {
    }

    public record CompleteMission(
            @NotNull Long missionId
    ) {
    }
}
