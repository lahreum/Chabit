# CHABIT

## 팀소개

- 팀명 : 살찐개
- 팀원 소개  
   팀장 : 김민혁  
   팀원 : 백정현, 이아름, 이동훈, 윤지해

## 주제

## 기술 스택

### Front-end

- Vue.js

### Back-end

- Spring boot
- JAVA
- MySQL

### DevOps

- Jenkins
- Docker
- Gitlab

## 주요 기능

## 개발 규칙

### Git

**Commit message**

```
Feat   : 코드나 테스트를 추가했을 때
Fix    : 버그를 수정했을 때
Remove : 코드를 제거했을 때
Update : 코드보다는 문서나 라이브러리 등을 보완했을 때
Docs   : 문서를 수정했을 때
Style  : 코드 포맷팅에 대한 부분 변경, CSS 등
Rename : 이름을 변경했을 때
Move   : 코드를 이동할 때
Refac  : 코드 리팩토링
```

**Branch**

- master
- develop
- feature
  ```
  예시) 로그인 기능의 경우 : feature/login
  ```

### Code

- 파스칼 케이스  
   Vue 파일, 컴포넌트 이름
- 케밥 케이스  
   html id, class
- 카멜 케이스  
   method 이름

## 프로젝트 일정

### Gantt chart

```mermaid
gantt
    dateFormat  YYYY-MM-DD
    title       CHABIT 2-3주차 진행상황
    %% excludes    2021-01-23,2021-01-24
    %% (`excludes` accepts specific dates in YYYY-MM-DD format, days of the week ("sunday") or "weekends", but not the word "weekdays".)

    section 프로젝트 기획
    기능 목록 상세 도출			:done, plan1, 2021-01-18, 2d
    화면 정의서				  :active, plan2, 2021-01-19, 2d
    와이어 프레임             :active, plan3, 2021-01-20, 2d

	section Front end
    UI 설계      	 :crit, front1, 2021-01-21, 2d
    디렉토리 구조 짜기		  	:  front2, 2021-01-22, 1d
    메인 화면 컴포넌트		 	 : front3, after front2, 3d
    헤더 컴포넌트   	  : front4, 2021-01-25, 2d
    푸터 컴포넌트         : front5, 2021-01-26, 1d
    회원 가입 컴포넌트  : front6, after front5, 2d

    section Back end
    MySQL & Spring Boot 환경 설정     	 : back1, 2021-01-22, 1d
    ER 다이어그램 작성     	 : back2, 2021-01-25, 1d
    DB 테이블 설계		:crit, back3, after back2, 3d

    section 배포
    AWS 설정 및 배포 환경 설정 :    deploy1, after back3, 1d
```
