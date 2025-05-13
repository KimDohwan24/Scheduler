package org.example.scheduler.repository;

import org.example.scheduler.dto.ScheduleResponseDto;
import org.example.scheduler.entity.Schedule;

import java.util.List;

public interface ScheduleRepository {
    ScheduleResponseDto saveSchedule(Schedule schedule);
}
