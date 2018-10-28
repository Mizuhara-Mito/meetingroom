import { Component, OnInit } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {ReservInfo} from '../_models/reservInfo';
import {Observable} from 'rxjs/index';

@Component({
  selector: 'app-input-form',
  templateUrl: './input-form.component.html',
  styleUrls: ['./input-form.component.css']
})
export class InputFormComponent implements OnInit {
  url = 'http://localhost:8080';
  rooms: any;
  reservDt: string;
  sttTime: string;
  endTime: string;
  userName: string;
  repeatNum: number;
  roomId: number;
  validationFlag = true;
  constructor(private http: HttpClient) {
    this.http.get(this.url + '/rooms').subscribe(result => {
      this.rooms = result;
    });
  }
  insertHipenInDate() {
    const inputLength = this.reservDt.length;
    if (inputLength === 4) {
      this.reservDt += '-';
    } else if (inputLength === 7) {
      this.reservDt += '-';
    }
  }
  checkSttTimeValid() {
    if ( this.sttTime !== '' && (Number(this.sttTime.substr(2, 2)) % 30) !== 0 ) {
      alert('시간은 30분 단위로 입력해주셔야 합니다.');
      this.validationFlag = false;
      return ;
    }
  }
  checkEndTimeValid() {
    if ( this.endTime !== '' && (Number(this.endTime.substr(2, 2)) % 30) !== 0 ) {
      alert('시간은 30분 단위로 입력해주셔야 합니다.');
      this.validationFlag = false;
      return ;
    }
  }
  addReservation() {
    const setTimeHour = this.sttTime.substr(0, 2);
    const setTimeMinute = this.sttTime.substr(2, 2);
    const endTimeHour = this.endTime.substr(0, 2);
    const endTimeMinute = this.endTime.substr(2, 2);
    const TimeDist = (Number(endTimeHour) - Number(setTimeHour)) * 60 + (Number(endTimeMinute) - Number(setTimeMinute));
    if ( TimeDist <= 120 && this.validationFlag) {
      this.doPost(setTimeHour + setTimeMinute, endTimeHour + endTimeMinute);
    } else if (TimeDist > 120) {
      alert('회의 시간은 2시간 이상일 수 없습니다.');
      this.validationFlag = false;
      return;
    }
  }
  doPost(setTime, endTime) {
    this.http.post(this.url + '/reserve', {
      'sttTime': setTime,
      'endTime': endTime,
      'userName': this.userName,
      'repeatNum': this.repeatNum,
      'reservDt': this.reservDt,
      'roomId': this.roomId
    }).subscribe(
      res => {
        Object.keys(res).map(function(idx) {
          console.log(res[idx].reservDate);
          console.log(res[idx].message);
        });
        alert('예약에 성공했습니다.');
      },
      err => {
        alert('예약에 실패했습니다.');
      }
    );
  }

  ngOnInit() {
  }
}
