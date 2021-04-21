import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Client } from '../client.class';
import { ClientService } from '../client.service';
import { MessageService } from 'primeng/api';
import { ErrorHandlerService } from '../../core/error-handler.service';

@Component({
  selector: 'app-client-register',
  templateUrl: './client-register.component.html',
  styleUrls: ['./client-register.component.css']
})
export class ClientRegisterComponent implements OnInit {

  client = new Client();

  constructor(
    private clientService: ClientService,
    private messageService: MessageService,
    private errorHandler: ErrorHandlerService) {
  }

  ngOnInit() {
  }

  save(f: any) {
    this.clientService.addClient(this.client)
      .then(savedClient => {
        this.messageService.add({severity: 'success', detail: 'Successfully added client'});
        console.log('Successfully added client', savedClient);
      })
      .catch(erro => this.errorHandler.handle(erro));
  }

  new(f: NgForm) {

  }
}
