# cloudTest

### AWS Budget 설정
![image (1).png](image%20%281%29.png)

### EC2 퍼블릭 IP
http://13.209.12.144:8080

## Lv. 1 - 네트워크 구축 및 핵심 기능 배포

### Actuator Health 엔드포인트
http://13.209.12.144:8080/actuator/health

---

## Lv. 2 - DB 분리 및 보안 연결

### Actuator Info 엔드포인트
http://13.209.12.144.8080/actuator/info

### RDS 보안 그룹 스크린샷
![RDS보안그룹.png](RDS%E1%84%87%E1%85%A9%E1%84%8B%E1%85%A1%E1%86%AB%E1%84%80%E1%85%B3%E1%84%85%E1%85%AE%E1%86%B8.png)

---

## Lv. 3 - S3 프로필 이미지

### Presigned URL
URL: [GET /api/members/{id}/profile-image 호출해서 받은 URL]
만료 시간: [발급일로부터 7일 후 날짜]

### 접근 성공 스크린샷
![img.png](img.png)
