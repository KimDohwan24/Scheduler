package org.example.scheduler.controller;


import lombok.RequiredArgsConstructor;
import org.example.scheduler.dto.ScheduleRequestDto;
import org.example.scheduler.dto.ScheduleResponseDto;
import org.example.scheduler.entity.Schedule;
import org.example.scheduler.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/schedules")
public class ScheduleController {
    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService){
        this.scheduleService = scheduleService;
    }

    // 스케줄 생성 API
    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createScheduler(@RequestBody ScheduleRequestDto requestDto){
        return new ResponseEntity<>(scheduleService.saveSchedule(requestDto), HttpStatus.CREATED);
    }

    // schedule 전체 조회
    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> findAllSchedules(){

//        return ResponseEntity.ok(scheduleService.findAllSchedules());
        return new ResponseEntity<>(scheduleService.findAllSchedules(),HttpStatus.OK);

    }

    // schedule 단건 조회
    @GetMapping("/{scheduleId}")
    public ResponseEntity<ScheduleResponseDto> findScheduleById(@PathVariable Long scheduleId){
        return new ResponseEntity<>(scheduleService.findScheduleById(scheduleId),HttpStatus.OK);
    }

    // schedule 수정
    @PutMapping("/{scheduleId}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(
            @PathVariable Long scheduleId,
            @RequestBody ScheduleRequestDto scheduleRequestDto
            ){
        return new ResponseEntity<>(scheduleService.updateSchedule(scheduleId,scheduleRequestDto.getTitle(),scheduleRequestDto.getContents(),scheduleRequestDto.getPassword()), HttpStatus.OK);
    }

    // schedule 삭제
    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<Void> deleteSchedule(
            @PathVariable Long scheduleId,
            @RequestBody ScheduleRequestDto scheduleRequestDto
    ){
        scheduleService.deleteSchedule(scheduleId,scheduleRequestDto.getPassword());

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
