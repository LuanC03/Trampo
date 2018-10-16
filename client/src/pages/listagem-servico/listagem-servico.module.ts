import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { ListagemServicoPage } from './listagem-servico';

@NgModule({
  declarations: [
    ListagemServicoPage,
  ],
  imports: [
    IonicPageModule.forChild(ListagemServicoPage),
  ],
})
export class ListagemServicoPageModule {}