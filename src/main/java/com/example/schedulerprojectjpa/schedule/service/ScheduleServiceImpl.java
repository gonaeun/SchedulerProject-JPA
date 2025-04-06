package com.example.schedulerprojectjpa.schedule.service;

import com.example.schedulerprojectjpa.schedule.dto.ScheduleRequestDto;
import com.example.schedulerprojectjpa.schedule.dto.ScheduleResponseDto;
import com.example.schedulerprojectjpa.schedule.entity.Schedule;
import com.example.schedulerprojectjpa.schedule.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Service  // 스프링 빈으로 등록
public class ScheduleServiceImpl implements com.example.schedulerprojectjpa.schedule.service.ScheduleService {
    private ScheduleRepository scheduleRepository;

    @Autowired
    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
        // 생성자 주입
        // Service 클래스에서 Repository 사용하려면, Repository를 클래스 안에 넣어줘야함
        // 이렇게 하면, 스프링이 자동으로 Repository Bean 주입
    }

    @Override
    public Schedule saveSchedule(Schedule schedule) {
        schedule.setCreated_date(LocalDateTime.now());
        schedule.setUpdated_date(LocalDateTime.now());
        return scheduleRepository.saveSchedule(schedule);
    }

    @Override
    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAllSchedules();
    }

    @Override
    public List<Schedule> getFilteredSchedules(String writer, LocalDate updated_date){
        return scheduleRepository.findAllSchedules().stream()
                .filter(schedule -> {
                    boolean matchWriter = (writer == null || writer.equals(schedule.getWriter()));
                    boolean matchDate = (updated_date == null || updated_date.equals(schedule.getUpdated_date().toLocalDate()));
                    return matchWriter || matchDate;  // 하나라도 true면 해당 schedule 객체 포함
                })
                .sorted(Comparator.comparing(Schedule::getUpdated_date).reversed())
                .collect(Collectors.toList());
        // Stream API를 활용해서 Schedule 리스트를 필터링, 정렬, 리스트로 반환하는 파이프라인.
    }

    @Override
    public Schedule getScheduleById(Long id) { // Service 계층에선 사용자가 api 읽기 좋게 "get" 표현
        Schedule schedule =  scheduleRepository.findScheduleById(id);  // Repository 계층에선 실제 데이터를 찾는 "find"대로 표현

        return schedule;
    }

    @Override
    public void deleteSchedule(Long id, String password) {
        Schedule schedule = scheduleRepository.findScheduleById(id); // 해당 id인 일정 찾기


        scheduleRepository.deleteSchedule(id);
    }

    @Override
    public ScheduleResponseDto updateSchedule(Long id, ScheduleRequestDto dto) {
        Schedule schedule = scheduleRepository.findScheduleById(id); // 해당 id인 일정 찾기

        // 바꾸고, 저장하고 (업데이트 기본로직)
        // 해당 id인 스케줄의 값을 dto의 값으로 바꾸기
        schedule.setTitle(dto.getTitle());  // setTitle() : 스케줄값을 이렇게 세팅할거야 >> 스케줄값을 dto값으로 바꿔줌
        schedule.setContent(dto.getContent());
        schedule.setWriter(dto.getWriter());

        scheduleRepository.updateSchedule(schedule);

        return new ScheduleResponseDto(schedule);
    }
}