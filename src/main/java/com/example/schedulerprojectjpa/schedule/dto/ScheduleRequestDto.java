package com.example.schedulerprojectjpa.schedule.dto;

import lombok.Getter;

// 클라이언트가 서버에게 요청 보낼 때 사용
// 사용자가 입력한 데이터만 포함
// 일정 수정/삭제 요청

@Getter
public class ScheduleRequestDto {
    private String title;
    private String content;
    private Long userId;  // writer을 userId로 수정
    private String password;
}
