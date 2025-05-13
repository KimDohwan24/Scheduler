package org.example.scheduler.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.sql.Timestamp;



@Getter
@Setter
@AllArgsConstructor
public class Schedule {
    private Long scheduleId;
    private String title;
    private String contents;
    private String password;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public Schedule(String title, String contents , String password) {
        this.title = title;
        this.contents = contents;
        this.password = password;
    }
}
