import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {AuthComponent} from "./components/auth/auth.component";
import {RegistrationComponent} from "./components/registration/registration.component";
import {ExchangeComponent} from "./components/exchange/exchange.component";
import {HistoryComponent} from "./components/history/history.component";
import {MainComponent} from "./components/main/main.component";

const routes: Routes = [


  {path:'login', component: AuthComponent },
  {path:'main', component: MainComponent,
    children: [{path:'history', component: HistoryComponent},
      {path:'exchange', component: ExchangeComponent},
    ]
  },
  {path:'logout', component: AuthComponent },
  {path:'register', component: RegistrationComponent },
  {path:'', redirectTo: '/login', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
