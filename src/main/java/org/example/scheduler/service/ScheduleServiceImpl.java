package org.example.scheduler.service;

import org.example.scheduler.dto.ScheduleRequestDto;
import org.example.scheduler.dto.ScheduleResponseDto;
import org.example.scheduler.entity.Schedule;
import org.example.scheduler.repository.ScheduleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

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
        List<ScheduleResponseDto> allSchedule = scheduleRepository.findAllSchedule();
        return allSchedule;
    }

    // 스케줄 단건 조회
    @Override
    public ScheduleResponseDto findScheduleById(Long scheduleId) {
        Schedule schedule = scheduleRepository.findScheduleByIdOrElseThrow(scheduleId);
        System.out.println(schedule.getCreatedAt());
        System.out.println(schedule.getUpdatedAt());
        return new ScheduleResponseDto(schedule);
    }

    // 스케줄 수정
    @Transactional
    @Override
    public ScheduleResponseDto updateSchedule(Long scheduleId, String title, String contents, String password) {
        Schedule schedule = scheduleRepository.findScheduleByIdOrElseThrow(scheduleId);
        int updateRow = 0;

        if(password == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"The title and content are required values");
        } else if (schedule.getPassword().equals(password)) {
            updateRow = scheduleRepository.updateSchedule(scheduleId,title,contents);
        }

        if(updateRow == 0){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No data has been modifieds");
        }
        return new ScheduleResponseDto(schedule);
    }

    // schedule 삭제
    @Override
    public void deleteSchedule(Long scheduleId, String password) {
        Schedule schedule = scheduleRepository.findScheduleByIdOrElseThrow(scheduleId);

        int deleteRow = 0;

        if(schedule.getPassword().equals(password)){
            deleteRow = scheduleRepository.deleteSchedule(scheduleId);
        }

        if(deleteRow == 0){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Does not exist id = " + schedule);
        }
    }
}