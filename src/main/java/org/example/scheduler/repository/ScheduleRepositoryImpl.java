package org.example.scheduler.repository;

import org.example.scheduler.dto.ScheduleResponseDto;
import org.example.scheduler.entity.Schedule;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@Repository
public class ScheduleRepositoryImpl implements ScheduleRepository {
    private final JdbcTemplate jdbcTemplate;

    public ScheduleRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    // 스케줄 생성 API
    @Override
    public ScheduleResponseDto saveSchedule(Schedule schedule) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        simpleJdbcInsert.withTableName("Schedule").usingGeneratedKeyColumns("scheduleId");

        // Mapping 하기
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("title", schedule.getTitle());
        parameters.put("contents", schedule.getContents());
        parameters.put("password", schedule.getPassword());

        schedule.setUpdatedAt(LocalDateTime.now());
        schedule.setCreatedAt(LocalDateTime.now());
        parameters.put("createdAt", schedule.getCreatedAt());
        parameters.put("updatedAt", schedule.getUpdatedAt());


        Number key = simpleJdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        // PostMan에 출력
        return new ScheduleResponseDto(key.longValue(), schedule.getTitle(), schedule.getContents(),schedule.getCreatedAt(),schedule.getUpdatedAt());
    }

}
