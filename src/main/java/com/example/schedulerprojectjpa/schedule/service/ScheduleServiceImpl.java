package com.example.schedulerprojectjpa.schedule.service;

import com.example.schedulerprojectjpa.schedule.dto.ScheduleRequestDto;
import com.example.schedulerprojectjpa.schedule.dto.ScheduleResponseDto;
import com.example.schedulerprojectjpa.schedule.entity.Schedule;
import com.example.schedulerprojectjpa.schedule.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Service  // 스프링 빈으로 등록
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepository scheduleRepository;

    @Autowired // 생성자 주입
    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public Schedule saveSchedule(Schedule schedule) {
//        schedule.setCreated_date(LocalDateTime.now());  // 엔터티에서 JPA Auditing 사용
//        schedule.setUpdated_date(LocalDateTime.now());  // 엔터티에서 JPA Auditing 사용
        return scheduleRepository.save(schedule);
    }

    @Override
    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    @Override
    public List<Schedule> getFilteredSchedules(String writer, LocalDate updated_date){
        return scheduleRepository.findAll().stream()
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
    public Schedule getScheduleById(Long id) {
        return scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 스케줄을 찾을 수 없습니다."));
    }

    @Override
    public void deleteSchedule(Long id, String password) {
        Schedule schedule = getScheduleById(id); // 해당 id인 일정 찾기

        if (!schedule.getPassword().equals(password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다");
        }

        scheduleRepository.deleteById(id);
    }

    @Override
    public ScheduleResponseDto updateSchedule(Long id, ScheduleRequestDto dto) {
        Schedule schedule = getScheduleById(id); // 해당 id인 일정 찾기

        // 바꾸고, 저장하고 (업데이트 기본로직)
        // 해당 id인 스케줄의 값을 dto의 값으로 바꾸기
        schedule.setTitle(dto.getTitle());  // setTitle() : 스케줄값을 이렇게 세팅할거야 >> 스케줄값을 dto값으로 바꿔줌
        schedule.setContent(dto.getContent());
        schedule.setWriter(dto.getWriter());

        Schedule updated = scheduleRepository.save(schedule);

        return new ScheduleResponseDto(updated);
    }
}