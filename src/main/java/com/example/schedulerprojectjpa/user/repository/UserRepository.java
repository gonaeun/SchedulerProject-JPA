package com.example.schedulerprojectjpa.user.repository;

import com.example.schedulerprojectjpa.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
