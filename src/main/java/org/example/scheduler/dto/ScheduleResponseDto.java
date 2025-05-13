package org.example.scheduler.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ScheduleResponseDto {
    private Long scheduleId;
    private final String title;
    private final String contents;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;
}
