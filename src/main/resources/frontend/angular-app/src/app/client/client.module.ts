import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ClientRegisterComponent } from './client-register/client-register.component';
import { ClientSearchComponent } from './client-search/client-search.component';

@NgModule({
  declarations: [ClientRegisterComponent, ClientSearchComponent],
  imports: [
    CommonModule
  ]
})
export class ClientModule { }
