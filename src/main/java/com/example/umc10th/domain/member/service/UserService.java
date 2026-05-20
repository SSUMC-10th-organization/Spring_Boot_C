package com.example.umc10th.domain.member.service;

import com.example.umc10th.domain.member.converter.UserConverter;
import com.example.umc10th.domain.member.dto.UserRequestDTO;
import com.example.umc10th.domain.member.dto.UserResponseDTO;
import com.example.umc10th.domain.member.entity.User;
import com.example.umc10th.domain.member.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserResponseDTO.SignupResultDTO joinUser(UserRequestDTO.SignupDTO request) {
        // 1. 비밀번호 암호화 (BCrypt)
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        // 2. DTO -> Entity 변환 (이전에 만든 컨버터 사용)
        User newUser = UserConverter.toUser(request, encodedPassword);

        // 3. DB에 저장
        User savedUser = userRepository.save(newUser);

        // 4. 저장된 Entity를 다시 응답 DTO로 변환하여 반환
        return UserConverter.toSignupResultDTO(savedUser.getId(), savedUser.getEmail(), savedUser.getName());
    }
}
