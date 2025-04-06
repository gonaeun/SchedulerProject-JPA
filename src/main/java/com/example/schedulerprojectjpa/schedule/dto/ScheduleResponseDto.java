package com.example.schedulerprojectjpa.schedule.dto;

// 서버가 클라이언트에게 응답 보낼 때 사용
// DB에서 꺼낸 모든 필드 (보안 때문에 password 제외)

import com.example.schedulerprojectjpa.schedule.entity.Schedule;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ScheduleResponseDto {

    private Long id;
    private String title;
    private String content;
    private String username;  // writer을 username으로 수정
    private LocalDateTime created_date;
    private LocalDateTime updated_date;

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.title = schedule.getTitle();
        this.content = schedule.getContent();
        this.username = schedule.getUser().getUsername();
        this.created_date = schedule.getCreated_date();
        this.updated_date = schedule.getUpdated_date();
    }
}
