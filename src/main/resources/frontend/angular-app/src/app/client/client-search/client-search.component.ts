import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-client-search',
  templateUrl: './client-search.component.html',
  styleUrls: ['./client-search.component.css']
})
export class ClientSearchComponent implements OnInit {
  clients;

  constructor() { }

  ngOnInit() {
    this.clients = [
      { name: 'Pedro Bacchini', email: 'pedrobacchini@outlook.com', birthdate: null },
      { name: 'Maria Silva', email: 'mariasilva@gamil.com', birthdate: '13/05/1990' }
    ];
  }

}
