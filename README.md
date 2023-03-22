# 블로그 검색 서비스
## 개발환경
- Springboot 3.0.4
- java 17
- gradle
- JPA
- h2

## 실행방법
1. 링크로 접속해 jar 파일 다운로드  
    - [jar 다운로드](https://drive.google.com/file/d/1h4tjUvBew809hnADNtF94PUxxOJJsG2H/view)
    - 흰색 창에서 조금 기다리면 파일 다운로드 시작
2. jar 파일이 있는 경로에서 `java -jar blog-search-service-0.0.1-SNAPSHOT.jar` 명령어 실행
3. 서버가 정상적으로 시작되면 http://localhost:8080 경로로 접속 

## API명세
### 블로그 검색
`GET /search`
### 인기검색어 목록
`GET /popular`




