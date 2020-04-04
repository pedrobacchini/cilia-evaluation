import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { Client } from './client.class';

@Injectable({
  providedIn: 'root'
})
export class ClientService {

  clientUrl: string;

  constructor(private http: HttpClient) {
    this.clientUrl = `${environment.apiUrl}/clients`;
  }

  addClient(client: Client): Promise<Client> {
    return this.http.post<Client>(this.clientUrl, client)
      .toPromise();
  }

  getAllClients(): Promise<Client[]> {
    return this.http.get<Client[]>(this.clientUrl)
      .toPromise();
  }
}
