import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ErrorHandlerService} from './error-handler.service';
import {PageNotFoundComponent} from './page-not-found/page-not-found.component';

@NgModule({
  declarations: [PageNotFoundComponent],
  imports: [CommonModule],
  providers: [ErrorHandlerService]
})
export class CoreModule {
}
