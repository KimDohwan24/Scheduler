package org.example.scheduler.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
public class Schedule {
    private Long scheduleId;
    private String title;
    private String contents;
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Schedule(String title, String contents , String password) {
        this.title = title;
        this.contents = contents;
        this.password = password;
    }

    public Schedule(Long scheduleId, String title, String contents,String password){
        this.scheduleId = scheduleId;
        this.title = title;
        this.contents = contents;
        this.password = password;
    }
}
