package org.example.scheduler.dto;

import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class ScheduleRequestDto {
    private String title;
    private String contents;
    private String password;
}
