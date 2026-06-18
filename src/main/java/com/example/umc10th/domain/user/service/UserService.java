package com.example.umc10th.domain.user.service;

import com.example.umc10th.domain.mission.converter.MissionConverter;
import com.example.umc10th.domain.mission.dto.MissionResponseDto;
import com.example.umc10th.domain.mission.enums.MissionStatus;
import com.example.umc10th.domain.user.converter.UserConverter;
import com.example.umc10th.domain.user.dto.AuthUser;
import com.example.umc10th.domain.user.dto.UserRequestDto;
import com.example.umc10th.domain.user.dto.UserResponseDto;
import com.example.umc10th.domain.user.entity.User;
import com.example.umc10th.domain.user.entity.UserMission;
import com.example.umc10th.domain.user.enums.UserErrorCode;
import com.example.umc10th.domain.user.repository.UserMissionRepository;
import com.example.umc10th.domain.user.repository.UserRepository;
import com.example.umc10th.global.apipayload.code.GeneralErrorCode;
import com.example.umc10th.global.apipayload.exception.GeneralException;
import com.example.umc10th.global.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final UserMissionRepository userMissionRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Transactional
    public UserResponseDto.SignUpResult signUp(UserRequestDto.SignUp request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new GeneralException(UserErrorCode.DUPLICATE_EMAIL);
        }
        String encodedPassword = passwordEncoder.encode(request.password());
        User user = UserConverter.toUser(request, encodedPassword);
        return UserConverter.toSignUpResult(userRepository.save(user));
    }

    public UserResponseDto.LoginResult login(UserRequestDto.Login request) {
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new GeneralException(UserErrorCode.INVALID_CREDENTIALS));
        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new GeneralException(UserErrorCode.INVALID_CREDENTIALS);
        }
        AuthUser authUser = new AuthUser(user);
        String accessToken = jwtUtil.createAccessToken(authUser);
        return new UserResponseDto.LoginResult(accessToken, user.getId(), user.getName());
    }

    public MissionResponseDto.MissionListResult getMyMissions(Long userId, MissionStatus status, int page) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "id"));
        Slice<UserMission> slice;
        if (status != null) {
            slice = userMissionRepository.findAllByUser_IdAndStatus(userId, status, pageable);
        } else {
            slice = userMissionRepository.findAllByUser_Id(userId, pageable);
        }
        return MissionConverter.toMissionListResult(slice);
    }

    public UserResponseDto.MyProfile getMyProfile(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.USER_NOT_FOUND));
        return UserConverter.toMyProfile(user);
    }

    @Transactional
    public UserResponseDto.CompleteMissionResult completeMission(Long userId, UserRequestDto.CompleteMission request) {
        UserMission userMission = userMissionRepository.findByUser_IdAndMission_Id(userId, request.missionId())
                .orElseThrow(() -> new GeneralException(GeneralErrorCode.MISSION_NOT_FOUND));
        if (userMission.getStatus() == MissionStatus.COMPLETE) {
            throw new GeneralException(GeneralErrorCode.MISSION_ALREADY_COMPLETE);
        }
        userMission.updateStatus(MissionStatus.COMPLETE);
        return UserConverter.toCompleteMissionResult(userMission);
    }
}
