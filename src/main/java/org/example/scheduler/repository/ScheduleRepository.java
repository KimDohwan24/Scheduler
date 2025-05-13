package org.example.scheduler.repository;

import org.example.scheduler.dto.ScheduleResponseDto;
import org.example.scheduler.entity.Schedule;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository {
    ScheduleResponseDto saveSchedule(Schedule schedule);

    List<ScheduleResponseDto> findAllSchedule();

    Schedule findScheduleByIdOrElseThrow(Long scheduleId);

}
