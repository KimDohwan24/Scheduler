package org.example.scheduler.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.scheduler.entity.Schedule;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ScheduleResponseDto {
    private Long scheduleId;
    private final String title;
    private final String contents;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public ScheduleResponseDto(Schedule schedule){
        this.scheduleId = schedule.getScheduleId();
        this.title = schedule.getTitle();
        this.contents = schedule.getContents();
        this.createdAt = schedule.getCreatedAt();
        this.updatedAt = schedule.getUpdatedAt();
    }
}
