package org.example.scheduler.repository;

import org.example.scheduler.dto.ScheduleResponseDto;
import org.example.scheduler.entity.Schedule;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

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

        schedule.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        schedule.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        parameters.put("createdAt", schedule.getCreatedAt());
        parameters.put("updatedAt", schedule.getUpdatedAt());


        Number key = simpleJdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        // PostMan에 출력
        return new ScheduleResponseDto(key.longValue(), schedule.getTitle(), schedule.getContents(),schedule.getCreatedAt(),schedule.getUpdatedAt());
    }

    // 스케줄 전체 조회
    @Override
    public List<ScheduleResponseDto> findAllSchedule() {
        return jdbcTemplate.query("select * from schedule", scheduleRowMapper());
    }

    // 스케줄 단건 조회
    @Override
    public Schedule findScheduleByIdOrElseThrow(Long scheduleId) {
        List<Schedule> result = jdbcTemplate.query("select * from schedule where scheduleId = ?",scheduleRowMapperV2(),scheduleId);
        return result.stream().findAny().orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Does not exists id = " + scheduleId));
    }

    // 스케줄 수정
    @Override
    public int updateSchedule(Long scheduleId, String title, String contents) {
        return jdbcTemplate.update("update schedule set title = ?, contents = ?, updatedAt = ? where scheduleId = ?",title,contents,Timestamp.valueOf(LocalDateTime.now()),scheduleId);
    }

    // 스케줄 삭제
    @Override
    public int deleteSchedule(Long scheduleId) {
        return jdbcTemplate.update("delete from schedule where scheduleId = ?",scheduleId);
    }


    // 데이터를 내 입맛에 맞게 등록
    private RowMapper<ScheduleResponseDto> scheduleRowMapper(){
        return new RowMapper<ScheduleResponseDto>() {
            // List<T>로 뱉음
            @Override
            public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new ScheduleResponseDto(
                        rs.getLong("scheduleId"),
                        rs.getString("title"),
                        rs.getString("contents"),
                        rs.getTimestamp("createdAt"),
                        rs.getTimestamp("updatedAt")
                );
            }
        };
    }

    private RowMapper<Schedule> scheduleRowMapperV2(){
        return new RowMapper<Schedule>() {
            @Override
            public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Schedule(
                        rs.getLong("scheduleId"),
                        rs.getString("title"),
                        rs.getString("contents"),
                        rs.getString("password"),
                        rs.getTimestamp("createdAt"),
                        rs.getTimestamp("updatedAt")
                );
            }
        };
    }
}

