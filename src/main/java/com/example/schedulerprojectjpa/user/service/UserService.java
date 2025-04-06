package com.example.schedulerprojectjpa.user.service;

import com.example.schedulerprojectjpa.user.dto.UserRequestDto;
import com.example.schedulerprojectjpa.user.dto.UserResponseDto;

import java.util.List;

public interface UserService {
    UserResponseDto createUser(UserRequestDto dto); // 유저 생성
    UserResponseDto getUserById(Long id); // 단건 유저 조회
    List<UserResponseDto> getAllUsers(); // 전체 유저 조회
    UserResponseDto updateUser(Long id, UserRequestDto dto); // 유저 정보 수정
    void deleteUser(Long id); // 유저 삭제
}
