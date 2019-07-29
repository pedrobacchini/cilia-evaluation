import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ClientRegisterComponent} from './client-register/client-register.component';
import {ClientSearchComponent} from './client-search/client-search.component';
import {FormsModule} from '@angular/forms';
import { InputTextModule } from 'primeng/components/inputtext/inputtext';
import { ButtonModule } from 'primeng/components/button/button';


@NgModule({
  declarations: [ClientRegisterComponent, ClientSearchComponent],
  imports: [
    CommonModule,
    FormsModule,

    InputTextModule,
    ButtonModule
  ]
})
export class ClientModule {
}
