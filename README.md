# 프로젝트 소개 및 목적
---
### 소개
부산광역시 폐기물 수수료 정보를 알려주는 프로젝트 자료입니다.

### 개발 목적
- 대형 폐기물을 버릴 때마다 해당하는 행정구역 사이트에서 수수료를 검색해야하는 수고로움을 덜고자 사이트를 제작하게 되었습니다.

### 개발 목표
- 사용하지 않는 중고 제품을 무료로 나눌 수 있는 게시판 만들기
- 부산광역시 구별 대형 폐기물을 버릴 때 발생하는 수수료를 검색할 수 있는 기능 구현하기

### 시연 영상
### [▶영상 보러가기](https://www.youtube.com/watch?v=qFSmtGsdRBI)

# 목차
---
- 팀소개
- 개발 일지
- 사용 기술
- 구현 기능
- Rest API
- ERD

# 팀소개
---
|담당|이름|github|
|------|---|---|
|FE|김혜정|https://github.com/maejyomi/WasteNow-front|
|BE|강희진|https://github.com/color7921/WasteNow_BE_Project|

# 개발 일지
---
<details>
  <summary style="font-size: 1.5em;">2023.11.14 ~ 2023.12.13 [click!] ◀</summary>
  <div>
  <p><span style="font-size: larger;"><1주차></span></p>
  <p>- [방향성 설정] 메인 화면, 로그인 화면 구성</p>
  <p>- Rest API 상세 목록 작성</p>
  <p>- [데이터 삽입]csv파일 데이터를 MYSQL로 Import하기, React & SpringBoot local 연결</p>
  <p>- [API 전송]리액트에서 지정된 값을 받고 MySQL에 저장된 지정값을 API 주소를 통해 리액트로 전송</p>
  </div>
  <p><span style="font-size: larger;"><2주차></span></p>
  <p>- 게시판, 댓글 데이터베이스 컬럼명 지정하기, 로그인, 로그아웃, 회원가입(중복 ID제거)</p>
  <p>- 쿼리 중복 제거</p>
  <p>- 게시판 리스트 목록 전송, 상세페이지 작성</p>
  <p>- 게시판 수정, 삭제</p>
  <p>- ERD 작성</p>
  <div></div>
  <p><span style="font-size: larger;"><3주차></span></p>
  <p>- 댓글 목록 리스트 전송 작성, 수정, 삭제</p>
  <p>- 게시글 상세 정보 기능 구현</p>
  <p>- 외래키 ManyToOne 구현</p>
  <p>- 게시글 조회수 증가 구현</p>
  <div></div>
  <p><span style="font-size: larger;"><4주차></span></p>
  <p>- 상세 페이지 username에 해당하는 레코드 보내기</p>
  <p>- 게시글 리스트 페이징 구현, 마이페이지 게시글 리스트, 댓글 리스트 전송</p>
  <div></div>
  <p><span style="font-size: larger;"><5주차></span></p>
</details>

<details>
  <summary style="font-size: 1.5em;">이슈 기록 [click!] ◀</summary>
  <p>[외래키 설정]</p>
  <p>ManyToOne fetch 옵션 FetchType.EAGER 기본값으로 설정되어 Comment 엔티티 조회 시 무조건 Post 객체를 가져옴</p>
  <p>[SpringBoot와 MySQL 표기법]</p>
  <p>SpringBoot에서 boardList라는 컬럼을 입력하면 MySQL에서는 board_List로 출력된다. Camel Case -> Snake Case</p>
  <p>[Cors]</p>
  <p>SecurityConfig 클래스의 requestMatchers(new AntPathRequestMatcher("/api/user/**")) 추가 후 문제 해결</p>
</details>
  
# 사용 기술
---
- SpringBoot 3.1.5
- SpringSecurity 6.1.5
- JSON Web Token 4.4.0
- MySQL connector j 8.0.33
- MySQL 8.0.34

# 구현 기능
---
|기능|설명|
|------|---|
|로그인, 회원가입|PostMapping으로 회원 정보를 저장 후, 로그인 기능 구현|
|수수료 검색|원하는 지역을 설정, 버리고자 하는 폐기물 선택, 검색어 입력을 하면 부산광역시 구별 대형 폐기물을 버릴 때 발생하는 수수료를 검색하는 정보창 구현|
|게시글 목록, 게시글 작성, 게시글 수정, 게시글 삭제, 게시글 검색|게시글 CRUD 구현|
|댓글 작성, 댓글 삭제, 댓글 수정|댓글 CRUD 구현|
|마이페이지|마이페이지를 클릭하면 내가 쓴 게시글과 댓글 리스트 출력|
|조회수, 페이징처리|게시글 상세 페이지를 클릭할 때마다 조회수 상승, 게시글 리스트 페이징 처리|
# Rest API
---

## 검색, 로그인, 회원가입
|Method|URI|설명|
|------|---|---|
|GET|api/search?sido={sido}&cate={cate}&keyword={keyword}|수수료 검색|
|POST|/login|로그인|
|POST|/api/signup|회원가입|

## 게시판
|Method|URI|설명|
|------|---|---|
|GET|/api/user/nowList?pageNo={pageNo}|게시글 리스트 전송|
|GET|/api/user/nowListSearch?keyword={keyword}|제목에 키워드가 포함되는 게시글 리스트 전송|
|GET|/api/user/nowList/board?postId={id}|게시글 상세 정보 전송|
|DELETE|/api/user/delBoard?postId={id}|아이디에 해당되는 게시글, 댓글 삭제|
|PUT|/api/user/updateNow|게시글 수정|
|POST|/api/user/nowWrite|게시글 작성|
|GET|/api/wastename?sido={sido}&cate={cate}|구, 카테고리에 해당되는 폐기물명 전송|
|GET|/api/wastesize?sido={sido}&cate={cate}&name={name}|구, 카테고리, 폐기물명에 해당되는 사이즈 전송|

## 댓글, 마이페이지
|Method|URI|설명|
|------|---|---|
|GET|/api/user/nowComment?postId={id}|아이디에 해당하는 게시글의 댓글 정보 전송|
|POST|/api/user/commWrite|댓글 작성|
|PUT|/api/user/updateComment|댓글 수정|
|DELETE|/api/user/delComment?commentId={id}|댓글 삭제|
|GET|/api/user/mypage?username={username}|해당 유저가 작성한 게시글, 댓글 리스트 전송|


# ERD
---
![erd](https://github.com/color7921/WasteNow_BE_Project/assets/132988693/cbbc8912-2af3-4f94-92c3-d4debeb977f1)
