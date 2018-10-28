export class ReservInfo {
  reservId: string;
  sttTime: string;
  endTime: string;
  roomId: number;
  userName: string;
  repeatNum: number;
  reservDt: string;
  constructor(sttTime: string, endTime: string, userName: string) {
    this.sttTime = sttTime;
    this.endTime = endTime;
    this.userName = userName;
  }
}
