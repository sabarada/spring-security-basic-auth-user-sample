## 개요

해당 프로젝트는 Spring Security를 이용하여 인증된 유저만 API를 호출할 수 있도록 하는 샘플 프로젝트입니다.

관련되어있는 블로그 내용은 아래와 같습니다.

- [[Spring Security + JPA] JPA를 사용해서 Spring Security User 인증 서비스 만들기](https://sabarada.tistory.com/242)
- [[Spring Security] 인증 Filter를 기준으로 Custom Filter 추가와 변경](https://sabarada.tistory.com/243)

## 환경

- database : H2
- kotlin : 1.6.21
- Spring Boot 2.7.5

- JPA
- Spring Security

## 사용 방법

### Service 실행

Spring Boot 서비스를 실행합니다. 서비스 실행으로 Embedded 되어있는 database가 시행되게 됩니다.

### h2 DB에 접속 

`localhost:8080/h2-console`에 접속합니다. 정상적으로 접속된다면 아래의 정보를 기입한 후 connect 버튼을 클릭하여 database에 접속합니다.

- Driver Class: org.h2.Driver
- JDBC URL: jdbc:h2:~/test
- User Name: sa
- Password: [빈칸 유지]

### user 데이터 insert

아래 쿼리를 이용하여 SABARADA_USER 테이블에 user 데이터 insert 

```sql
INSERT INTO SABARADA_USER VALUES('koangho93@naver.com', '1234', 'USER');
```

### Local Test

아래 API를 BasicAuth 인증 정보를 넣기 전/후로 호출하여 테스트를 진행 

```http request
localhost:8080/example
```

#### 아래는 호출 예시

```bash
-> curl -u koangho93@naver.com:1234 -i -X GET localhost:8080/example

HTTP/1.1 200
Set-Cookie: JSESSIONID=10433FAD12BBE42FF273C3638F083B32; Path=/; HttpOnly
X-Content-Type-Options: nosniff
X-XSS-Protection: 1; mode=block
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Pragma: no-cache
Expires: 0
X-Frame-Options: DENY
Content-Type: text/plain;charset=UTF-8
Content-Length: 4
Date: Sun, 18 Dec 2022 07:15:22 GMT
```
