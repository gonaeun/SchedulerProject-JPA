package com.example.schedulerprojectjpa.schedule.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Schedule {

    @Id // PK 나타냄
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // PK 값 자동생성 관리
    private Long id;

    @Column(nullable = false) // NOT NULL
    private String title; // 일정 제목

    @Column(nullable = false)
    private String writer; // 작성자명

    @Column(nullable = false)
    private String content; // 일정 내용

    @Column(nullable = false)
    private String password; // 비밀번호

    @Column(nullable = false, updatable = false) // 작성일은 수정 안됨
    private LocalDateTime created_date; // 게시물 작성일

    @Column(nullable = false)
    private LocalDateTime updated_date; // 게시물 수정일

//    public void updateTitle(ScheduleRequestDto scheduleRequestDto) {
//        this.title = scheduleRequestDto.getTitle();
//    }

}
