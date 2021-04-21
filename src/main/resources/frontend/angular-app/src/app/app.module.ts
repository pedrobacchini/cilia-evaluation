import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ClientModule } from './client/client.module';
import { ProductModule } from './product/product.module';
import { OrderModule } from './order/order.module';
import { GrowlModule } from 'primeng/growl';
import { CoreModule } from './core/core.module';
import { MessageService } from 'primeng/components/common/messageservice';

@NgModule({
  declarations: [AppComponent],
  imports: [
    BrowserModule,
    AppRoutingModule,
    GrowlModule,

    ClientModule,
    ProductModule,
    OrderModule,
    CoreModule
  ],
  exports: [GrowlModule],
  providers: [MessageService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
