import { Component } from '@angular/core';
import { IonicPage, NavController, AlertController } from 'ionic-angular';
import { LoginPage } from '../login/login';
import { AutenticacaoService } from '../../services/autenticacao.service';


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

  constructor(public navCtrl: NavController)     {
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad HomePage');
  }

  logout(){

    this.navCtrl.setRoot(LoginPage);
  }

}
