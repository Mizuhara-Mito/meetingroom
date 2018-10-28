import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {ReservInfo} from '../_models/reservInfo';

@Component({
  selector: 'app-timetable',
  templateUrl: './timetable.component.html',
  styleUrls: ['./timetable.component.css']
})
export class TimetableComponent implements OnInit {

  url: String = 'http://localhost:8080'
  reservInfoBySrch: any;
  inputDate: String;
  reservInfo: any[] = new Array();
  rooms: any;
  constructor(private http: HttpClient) {
    this.http.get(this.url + '/rooms').subscribe(result => {
      this.rooms = result;
      this.setRoomsNum();
    });
  }
  setRoomsNum() {
    for (let i = 0 ; i < this.rooms.length; i++) {
      this.reservInfo.push([]);
    }
  }
  retrieveReservInfoByDate() {
    this.http.get(this.url + '/retrieveReservation/' + this.inputDate)
      .subscribe(result => {
        this.reservInfo = new Array();
        this.setRoomsNum();
        this.reservInfoBySrch = result;
        this.devideReservInfoByRoom();
      });
  }
  devideReservInfoByRoom() {
    for (let i = 0; i < this.reservInfoBySrch.length; i++) {
      const roomId: number = this.reservInfoBySrch[i].roomId - 1;
      this.reservInfo[roomId].push(new ReservInfo(
        this.parseTime(this.reservInfoBySrch[i].sttTime),
        this.parseTime(this.reservInfoBySrch[i].endTime),
        this.reservInfoBySrch[i].userName
      ));
    }
  }
  parseTime(time: string) {
    return time.substr(0, 2) + ':' + time.substr(2, 2);
  }
  insertHipen() {
    const inputLength = this.inputDate.length;
    if(inputLength === 4) {
      this.inputDate += '-';
    } else if(inputLength === 7) {
      this.inputDate += '-';
    }
  }
  ngOnInit() {
  }
}
