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
