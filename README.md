<H1>API 명세서 작성</H1>

| **기능** | **Method** | **URL** | **request** | **상태코드** |
| --- | --- | --- | --- | --- |
| 스케줄 생성 | POST | /schudules | 요청 body | 200 : 정상 등록 |
| 스케줄 전체 조회 | GET | /schedules/{scheduleId} | 요청 param | 200 : 정상 등록  <br>404 : NOT_FOUND |
| 스케줄 단건 조회 | GET | /schedules/{scheduleId} | 요청 param | 200 : 정상 등록  <br>404 : NOT_FOUND |
| 스케줄 수정 | PUT | /schedules/{scheduleId} | 요청 body | 200 : 정상 등록  <br>404 : NOT_FOUND <br> 400 비밀번호 오류 |
| 스케줄 삭제 | DELETE | /schedules/{scheduleId} | 요청 param | 200 : 정상 등록  <br>404 : NOT_FOUND <br> 400 비밀번호 오류  |

<H1> ERD </H1>

| Column Name | Type          | Description           |
|-------------|---------------|------------------------|
| scheduleId  | BIGINT        | Primary Key, Auto Increment |
| createdAt   | TIMESTAMP     | 생성 시각               |
| updatedAt   | TIMESTAMP     | 수정 시각               |
| title       | VARCHAR(50)   | 제목                   |
| contents    | TEXT          | 내용                   |
| password    | TEXT          | 비밀번호               |




<H1> SQL문 </H1>

```sql
create table schedule(
    scheduleId BIGINT AUTO_INCREMENT PRIMARY KEY,
    createdAt TIMESTAMP,
    updatedAt TIMESTAMP,
    title VARCHAR(50),
    contents TEXT,
    password TEXT
)
```

