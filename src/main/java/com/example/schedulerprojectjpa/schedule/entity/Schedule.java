package com.example.schedulerprojectjpa.schedule.entity;

import com.example.schedulerprojectjpa.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Schedule {

    @Id // PK 나타냄
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // PK 값 자동생성 관리
    private Long id;

    @Column(nullable = false) // NOT NULL
    private String title; // 일정 제목

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)  // FK, 유저 고유 식별자 필드로 대체
    private User user; // 작성자명

    @Column(nullable = false)
    private String content; // 일정 내용

    @Column(nullable = false)
    private String password; // 비밀번호

    @CreatedDate // JPA Auditing 사용
    @Column(nullable = false, updatable = false) // 작성일은 수정 안됨
    private LocalDateTime created_date; // 게시물 작성일

    @LastModifiedDate // JPA Auditing 사용
    @Column(nullable = false)
    private LocalDateTime updated_date; // 게시물 수정일

}
