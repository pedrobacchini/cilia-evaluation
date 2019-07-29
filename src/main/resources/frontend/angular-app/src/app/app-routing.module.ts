import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {PageNotFoundComponent} from './page-not-found/page-not-found.component';
import {ClientRegisterComponent} from './client/client-register/client-register.component';

const routes: Routes = [
  {path: '', redirectTo: 'client', pathMatch: 'full'},
  {path: 'client', component: ClientRegisterComponent},
  {path: 'page-not-found', component: PageNotFoundComponent},
  {path: '**', redirectTo: 'page-not-found'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
