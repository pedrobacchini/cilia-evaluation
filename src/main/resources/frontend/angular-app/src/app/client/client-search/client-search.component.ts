import {Component, OnInit} from '@angular/core';
import {ClientService} from '../client.service';
import {ErrorHandlerService} from '../../core/error-handler.service';

@Component({
  selector: 'app-client-search',
  templateUrl: './client-search.component.html',
  styleUrls: ['./client-search.component.css']
})
export class ClientSearchComponent implements OnInit {

  clients;
  cols: any[];

  constructor(
    private clientService: ClientService,
    private errorHandler: ErrorHandlerService
  ) {
    this.clientService.getAllClients()
      .then(newClients => {
        this.clients = newClients;
        console.log('Successfully get all clients', newClients);
      })
      .catch(erro => this.errorHandler.handle(erro));
  }

  ngOnInit() {
    this.cols = [
      {field: 'name', header: 'Name'},
      {field: 'email', header: 'Email'},
      {field: 'birthdate', header: 'Birthdate'}
    ];
  }

}
