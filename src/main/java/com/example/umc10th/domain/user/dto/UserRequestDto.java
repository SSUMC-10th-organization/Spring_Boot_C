package com.example.umc10th.domain.user.dto;

import com.example.umc10th.domain.user.enums.Address;
import com.example.umc10th.domain.user.enums.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public class UserRequestDto {

    public record Create() {
    }

    public record SignUp(
            @NotBlank String id,
            @NotBlank String password,
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
