import { Component } from '@angular/core';
import { IonicPage, NavController } from 'ionic-angular';

import { AutenticacaoService } from '../../services/autenticacao.service';
import { StorageService } from '../../services/storage.service';


@IonicPage()
@Component({
  selector: 'page-listagem',
  templateUrl: 'listagem-servico.html',
})
export class ListagemServicoPage {

  user: string;

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

  ionBackPage(){
    this.navCtrl.setRoot('HomePage');
  }

}
