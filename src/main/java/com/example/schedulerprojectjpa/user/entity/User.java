package com.example.schedulerprojectjpa.user.entity;

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
@EntityListeners(AuditingEntityListener.class)  // @CreatedDate, @LastModifiedDate 을 동작시키는 역할
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @CreatedDate  // JPA Auditing 사용
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate  // JPA Auditing 사용
    @Column(nullable = false)
    private LocalDateTime updatedDate;
}
