package com.example.schedulerprojectjpa.schedule.repository;

import com.example.schedulerprojectjpa.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}

