import { Component } from '@angular/core';
import { IonicPage, NavController } from 'ionic-angular';

import { AutenticacaoService } from '../../services/autenticacao.service';
import { StorageService } from '../../services/storage.service';
import { ServicoClienteService } from '../../services/servico-cliente.service';
import { ServicoClienteDTO } from '../../models/servico-cliente.dto';


@IonicPage()
@Component({
  selector: 'page-listagem',
  templateUrl: 'listagem-servico.html',
})
export class ListagemServicoPage {

  user: string;
  servicos: ServicoClienteDTO[];

  constructor(public navCtrl: NavController,
    public autenticacaoService: AutenticacaoService,
    public storageService: StorageService,
    public servicoClienteService: ServicoClienteService)     {
      this.getServicosCliente();
  }

  ionViewDidLoad() {
    let localUser = this.storageService.getLocalUser();
    if (localUser && localUser.username){
      this.user = localUser.username;
    }
  }

  ionBackPage(){
    this.navCtrl.setRoot('HomePage');
  }

  getServicosCliente(){
    this.servicoClienteService.getServicos().subscribe(
        response => {
          this.servicos = response.body['data'];
          console.log(this.servicos);
        }
    );
  }

  openDetalhes(servico: ServicoClienteDTO){
    this.navCtrl.push('DetalheServicoPage', servico)
  }

}
