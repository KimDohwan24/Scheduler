package org.example.scheduler.service;

import org.example.scheduler.dto.ScheduleRequestDto;
import org.example.scheduler.dto.ScheduleResponseDto;
import org.example.scheduler.entity.Schedule;
import org.example.scheduler.repository.ScheduleRepository;
import org.springframework.http.ResponseEntity;
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

    // 스케줄 전체 조회 API
    @Override
    public List<ScheduleResponseDto> findAllSchedules() {
        // 전체 조회
        List<ScheduleResponseDto>  allSchedule = scheduleRepository.findAllSchedule();
        return allSchedule;
    }

    // 스케줄 단건 조회
    @Override
    public ScheduleResponseDto findScheduleById(Long scheduleId) {
        Schedule schedule = scheduleRepository.findScheduleByIdOrElseThrow(scheduleId);

        return new ScheduleResponseDto(schedule);
    }
}