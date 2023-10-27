import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RandomBookComponent } from './random-book/random-book.component';

const routes: Routes = [  
  { path: 'randomBook', component : RandomBookComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }
