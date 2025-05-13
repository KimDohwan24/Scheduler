package org.example.scheduler.repository;

import org.example.scheduler.dto.ScheduleResponseDto;
import org.example.scheduler.entity.Schedule;
import java.util.List;


public interface ScheduleRepository {
    ScheduleResponseDto saveSchedule(Schedule schedule);

    List<ScheduleResponseDto> findAllSchedule();

    Schedule findScheduleByIdOrElseThrow(Long scheduleId);

    int updateSchedule(Long scheduleId,String title,String contents);

    int deleteSchedule(Long scheduleId);
}
