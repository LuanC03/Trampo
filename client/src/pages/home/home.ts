import { Component } from '@angular/core';
import { IonicPage, NavController } from 'ionic-angular';
import { LoginPage } from '../login/login';
//import { DetalheServicoPage } from '../detalhe-servico/detalhe-servico';
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
  selector: 'page-home',
  templateUrl: 'home.html',
})
export class HomePage {

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

  logout(){
    this.autenticacaoService.logout();
    this.navCtrl.setRoot(LoginPage);
  }
  
  ionViewDetails() {
    this.navCtrl.push('DetalheServicoPage');
    //this.navCtrl.setRoot(DetalheServicoPage);

  }
      
  requisitionService() {
     this.navCtrl.push('RequisicaoServicoPage'); 
     //this.navCtrl.setRoot(RequisicaoServicoPage);
  }

  listService() {
     this.navCtrl.push('ListagemServicoPage'); 
     //this.navCtrl.setRoot(RequisicaoServicoPage);
  }
}