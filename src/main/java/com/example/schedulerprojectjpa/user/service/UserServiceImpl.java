package com.example.schedulerprojectjpa.user.service;

import com.example.schedulerprojectjpa.config.PasswordEncoder;
import com.example.schedulerprojectjpa.user.dto.UserRequestDto;
import com.example.schedulerprojectjpa.user.dto.UserResponseDto;
import com.example.schedulerprojectjpa.user.entity.User;
import com.example.schedulerprojectjpa.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // 생성자 주입
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserResponseDto createUser(UserRequestDto dto) { // 유저 생성
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword())); // 비밀번호를 암호화 상태로 저장
        return new UserResponseDto(userRepository.save(user));
    }

    @Override
    public UserResponseDto getUserById(Long id) { // 단건 유저 조회
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 유저가 없습니다."));
        return new UserResponseDto(user);
    }

    @Override
    public List<UserResponseDto> getAllUsers() { // 전체 유저 조회
        return userRepository.findAll().stream()
                .map(UserResponseDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDto updateUser(Long id, UserRequestDto dto) { // 유저 수정
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 유저가 없습니다."));
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        return new UserResponseDto(userRepository.save(user));
    }

    @Override
    public void deleteUser(Long id) { // 유저 삭제
        userRepository.deleteById(id);
    }
}
