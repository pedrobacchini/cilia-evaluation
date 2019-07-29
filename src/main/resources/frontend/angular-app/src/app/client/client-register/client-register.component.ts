import { Component, OnInit } from '@angular/core';
import {NgForm} from '@angular/forms';

class Client {
  uuid: string;
  name: string;
  email: string;
}

@Component({
  selector: 'app-client-register',
  templateUrl: './client-register.component.html',
  styleUrls: ['./client-register.component.css']
})
export class ClientRegisterComponent implements OnInit {

  client = new Client();

  constructor() { }

  ngOnInit() {
  }

  save(f: any) {

  }

  new(f: NgForm) {

  }
}
