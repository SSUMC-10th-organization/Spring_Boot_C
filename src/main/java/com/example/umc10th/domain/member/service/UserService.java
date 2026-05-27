package com.example.umc10th.domain.member.service;

import com.example.umc10th.domain.member.converter.UserConverter;
import com.example.umc10th.domain.member.dto.UserRequestDTO;
import com.example.umc10th.domain.member.dto.UserResponseDTO;
import com.example.umc10th.domain.member.entity.User;
import com.example.umc10th.domain.member.repository.UserRepository;
import com.example.umc10th.global.jwt.JwtTokenProvider;
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
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public UserResponseDTO.SignupResultDTO joinUser(UserRequestDTO.SignupDTO request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }

        String encodedPassword = passwordEncoder.encode(request.getPassword());

        User newUser = UserConverter.toUser(request, encodedPassword);
        User savedUser = userRepository.save(newUser);

        return UserConverter.toSignupResultDTO(
                savedUser.getId(),
                savedUser.getEmail(),
                savedUser.getName()
        );
    }

    public UserResponseDTO.LoginResultDTO login(UserRequestDTO.LoginDTO request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 이메일입니다."));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        String accessToken = jwtTokenProvider.createAccessToken(user.getId(), user.getEmail());

        return UserConverter.toLoginResultDTO(accessToken);
    }

    public UserResponseDTO.MyPageResultDTO getMyPage(User user) {
        return UserConverter.toMyPageResultDTO(user);
    }
}
