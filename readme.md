## 프로젝트 구조
backend : springboot 프로젝트   
frontend : angular 프로젝트

## Build
backend : gradle clean build bootRun  
**-> 접속주소 localhost:8080** 

frontend : 
1. npm install  
2. ng serve  

**-> 접속주소 localhost:4200**

This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 7.0.3.

## angular-cli가 설치되지 않은 경우

npm install -g @angular/cli

## ng command를 찾을 수 없는 경우
alias ng="{@angular/cli위치}/bin/ng"  
예 : alias ng="/usr/local/Cellar/node/8.9.0/lib/node_modules/@angular/cli/bin/ng"

## h2 console 접속정보
1. 주소 -> **http://localhost:8080/console/**
2. ID : root
3. PW : root

## 문제 해결 전략
1. 기술 선정
 * 백엔드 : 비교적 간단한 restAPI를 개발하는 문제이므로 백엔드 기술로 최소한의 세팅으로 로직 구현에 집중이 가능한 Spring boot, h2 DB, JPA 선정.  
 * 프론트엔드 : 타입스크립트 기반으로 모델 객체를 다루기 용이하며 angular-cli, angular-material 등의 도구로 프로젝트를 구성하기 용이한 Angular 선택 .
2. 해결 순서
 * 프로젝트 구조 설계 및 세팅 : 프론트와 백엔드를 나누고 restAPI를 통해 통신하도록 구조 설계후 세팅.
 * 예약정보 받아오기 : GET 방식으로 예약기준 날짜를 통해 조회한 예약정보를 화면으로 받아옴.
 * 예약하기 : 화면에서 Validation을 거친 후 POST 방식으로 예약정보를 전송함.
 * 반복처리 : 반복해서 예약처리를 하돼 각 케이스마다 중복검사 결과를 리턴함.
3. 핵심 전략  
* 예약중복 판단 : 예약신청 시작시간과 종료시간을 기준으로, 그 사이에 있는 다른 회의의 시작시간과 종료시간을 검사. 해당되는 recode가 있을시 중복 판단
* 반복 예약 : 반복 예약 중 중복예약으로 판단되면 예약하지 않고 skip. 성공한 예약과 중복 판단 예약의 정보를 return 해줌.
* SystemMessage : 다(반복)건의 예약 발생시 각 예약건의 성공, 중복 여부를 알기위해 예약 메서드에서 SystemMessage 객체를 만들어 해당 예약 날짜의 예약성공 유무를 리턴함. 이를 리스트에 담아 화면에 넘겨주고, 화면에서는 각 예약마다 성공, 중복 여부를 확인할 수 있음 

