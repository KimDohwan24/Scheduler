package org.example.scheduler.service;

import org.example.scheduler.dto.ScheduleRequestDto;
import org.example.scheduler.dto.ScheduleResponseDto;
import org.example.scheduler.entity.Schedule;
import org.example.scheduler.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    private ScheduleRepository scheduleRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }


    // 스케줄 생성 API
    @Override
    public ScheduleResponseDto saveSchedule(ScheduleRequestDto requestDto) {

        // 요청받은 데이터로 Schedule 객체 생성
        Schedule schedule = new Schedule(requestDto.getTitle(), requestDto.getContents(), requestDto.getPassword());
        ScheduleResponseDto scheduleResponseDto = scheduleRepository.saveSchedule(schedule);

        // DB에 전달
        return scheduleResponseDto;
    }
}

/*
* User -> controller -> Service -> Repository -> DB
* DTO ( Data Transfer Object )
*/