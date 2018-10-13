import { Component } from '@angular/core';
import { IonicPage, NavController } from 'ionic-angular';

import { AutenticacaoService } from '../../services/autenticacao.service';
import { StorageService } from '../../services/storage.service';
import { NavParams } from 'ionic-angular/navigation/nav-params';
import { ServicoClienteDTO } from '../../models/servico-cliente.dto';


@IonicPage()
@Component({
  selector: 'page-detalhes',
  templateUrl: 'detalhe-servico.html',
})
export class DetalheServicoPage {

  user: string;
  servico: ServicoClienteDTO = {
    data: "",
    horario: "",
    valor: "",
    tipo: "",
    endereco: {
    rua: "",
    bairro: "",
    numero: ""
    },
    fornecedor: "",
    status: "",
    cliente: ""
  };

  constructor(public navCtrl: NavController,
    public autenticacaoService: AutenticacaoService,
    public storageService: StorageService,
    public navParams: NavParams)     {
  }

  ionViewDidLoad() {
    let localUser = this.storageService.getLocalUser();
    if (localUser && localUser.username){
      this.user = localUser.username;
    }
    this.servico.tipo = this.navParams.get("tipo");
    this.servico.data = this.navParams.get("data");
    this.servico.horario = this.navParams.get("horario");
    this.servico.valor = this.navParams.get("valor");
    this.servico.status = this.navParams.get("status");
    this.servico.endereco = this.navParams.get("endereco");

  }

  ionBackPage(){
    this.navCtrl.pop();
  }

}
