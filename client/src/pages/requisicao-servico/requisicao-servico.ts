import { Component } from '@angular/core';
import { IonicPage, NavController } from 'ionic-angular';

import { LoginPage } from '../login/login';
import { DadosUsuarioDTO } from '../../models/dados-usuario.dto';

import { AutenticacaoService } from '../../services/autenticacao.service';
import { StorageService } from '../../services/storage.service';


/**
 * Generated class for the HomePage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

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
    public storageService: StorageService)     {
  }

  ionViewDidLoad() {
    let localUser = this.storageService.getLocalUser();
    if (localUser && localUser.email){
      this.user = localUser.email;
    }
  }

  logout() {
    this.autenticacaoService.logout();
    this.navCtrl.setRoot(LoginPage);
  }

  ionBackPage() {
    this.navCtrl.push('HomePage');
  }

}
