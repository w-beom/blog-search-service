# 블로그 검색 서비스
## 개발환경
- Springboot 3.0.4
- java 17
- gradle
- JPA
- h2

## 실행방법
1. 링크로 접속해 jar 파일 다운로드
    - 아래 2개의 링크중 1개 선택하여 다운로드
      - [github 다운로드](https://github.com/w-beom/blog-search-service/blob/master/blog-search-service-0.0.1-SNAPSHOT.jar)
      - [구글 드라이브 jar 다운로드](https://drive.google.com/file/d/1oK5qt_DV6Afegw6IXQsTjtCQSEfVK1yS/view?usp=sharing) `(흰색 창에서 조금 기다리면 파일 다운로드 시작)`
2. jar 파일이 있는 경로에서 `java -jar blog-search-service-0.0.1-SNAPSHOT.jar` 명령어 실행
3. 서버가 정상적으로 시작되면 http://localhost:8080 경로로 접속 

## API명세
### 블로그 검색
`GET /search`
### Request
|Name|Type|Description|	Required|
|------|---|---|--|
|query|String|검색을 원하는 질의어|O|
|sort|String|결과 문서 정렬 방식, accuracy(정확도순) 또는 recency(최신순), 기본 값 accuracy|X|
|page|Integer|결과 페이지 번호, 1~50 사이의 값, 기본 값 1|X|
|size|Integer|한 페이지에 보여질 문서 수, 1~50 사이의 값, 기본 값 10|X|
### Response
**meta**
|Name|Type|Description|
|------|---|---|
|total_count|Integer|검색된 문서 수|
|pageable_count|Integer|total_count 중 노출 가능 문서 수|
|is_end|Boolean|현재 페이지가 마지막 페이지인지 여부, 값이 false면 page를 증가시켜 다음 페이지를 요청할 수 있음|

**documents**

|Name|Type|Description|
|------|---|---|
|title|String|블로그 글 제목|
|contents|String|블로그 글 요약|
|url|String|블로그 글 URL|
|cafename|String|블로그의 이름|
|thumbnail|String|검색 시스템에서 추출한 대표 미리보기 이미지 URL, 미리보기 크기 및 화질은 변경될 수 있음|
|datetime|Datetime|블로그 글 작성시간, ISO 8601[YYYY]-[MM]-[DD]T[hh]:[mm]:[ss].000+[tz]|

### 인기검색어 목록
`GET /popular`
### Response
|Name|Type|Description|
|------|---|---|
|query|String|검색 질의어|
|queryCount|Integer|검색 횟수|
