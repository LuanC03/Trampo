import { Component } from '@angular/core';
import { IonicPage, NavController, Events, NavParams } from 'ionic-angular';
import { LoginPage } from '../login/login';
import { AutenticacaoService } from '../../services/autenticacao.service';
import { StorageService } from '../../services/storage.service';
import { UsuarioService } from '../../services/usuario.service';

@IonicPage()
@Component({
  selector: 'page-home',
  templateUrl: 'home.html',
})
export class HomePage {

  user: string;
  tipo_usuario: string;

  constructor(public navCtrl: NavController,
    public autenticacaoService: AutenticacaoService,
    public storageService: StorageService,
    public usuarioService: UsuarioService,
    public events: Events,
    public navParams: NavParams)     {
  }

  ionViewDidLoad() {
    let localUser = this.storageService.getLocalUser();
    if (localUser && localUser.username){
      this.user = localUser.username;
    }
    this.tipo_usuario = this.navParams.get("tipo");
  }

  logout(){
    this.autenticacaoService.logout();
    this.navCtrl.setRoot(LoginPage);
  }
  
}