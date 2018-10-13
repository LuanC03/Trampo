import { Component } from '@angular/core';
import { IonicPage, NavController, MenuController } from 'ionic-angular';

import { DadosUsuarioDTO } from '../../models/dados-usuario.dto';

import { AutenticacaoService } from '../../services/autenticacao.service';
import { StorageService } from '../../services/storage.service';

@IonicPage()
@Component({
  selector: 'page-requisicao',
  templateUrl: 'requisicao-servico.html',
})
export class RequisicaoServicoPage {

  user: string;
  especialidades : string[] = ["Chaveiro","Encanador", "Marceneiro", "Motorista","Pedreiro" ];

  dados_fornecedor : DadosUsuarioDTO = {
    fotoPerfil : "",
    nomeCompleto : "",
    login : "",
    email : "",
    senha : "",
    conf_senha : "",
    listaEspecialidades : []
  };


  constructor(public navCtrl: NavController,
    public autenticacaoService: AutenticacaoService,
    public storageService: StorageService,
    public menuCtrl: MenuController)     {
  }

  ionViewDidLoad() {
    let localUser = this.storageService.getLocalUser();
    if (localUser && localUser.email){
      this.user = localUser.email;
    }
  }

  ionBackPage() {
    this.navCtrl.setRoot('HomePage');
  }

}
