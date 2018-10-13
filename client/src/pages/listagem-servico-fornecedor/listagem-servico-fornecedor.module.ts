import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { ListagemServicoFornecedorPage } from './listagem-servico-fornecedor';

@NgModule({
  declarations: [
    ListagemServicoFornecedorPage,
  ],
  imports: [
    IonicPageModule.forChild(ListagemServicoFornecedorPage),
  ],
})
export class ListagemServicoFornecedorPageModule {}
