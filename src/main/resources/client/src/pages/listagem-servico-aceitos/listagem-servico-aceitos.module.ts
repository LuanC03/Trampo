import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { ListagemServicoAceitosPage } from './listagem-servico-aceitos';

@NgModule({
  declarations: [
    ListagemServicoAceitosPage,
  ],
  imports: [
    IonicPageModule.forChild(ListagemServicoAceitosPage),
  ],
})
export class ListagemServicoAceitosPageModule {}
