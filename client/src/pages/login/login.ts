import { Component } from '@angular/core';
import { NavController, LoadingController, AlertController, IonicPage } from 'ionic-angular';
import { CredenciaisDTO } from '../../models/credenciais.dto';

@IonicPage()
@Component({
  selector: 'page-login',
  templateUrl: 'login.html'
})
export class LoginPage {

  creds : CredenciaisDTO = {
    username : "",
    password : ""
  };

  constructor(public navCtrl: NavController, 
    private alertCtrl: AlertController,
    public loadingCrtl: LoadingController) {
  	
  }

  cadastroCliente(){
    this.navCtrl.push('CadastroClientePage');
  }

  cadastroFornecedor(){
    this.navCtrl.push('CadastroFornecedorPage');
  }

  redefinicaoSenha(){
    this.navCtrl.push('RedefinicaoSenhaPage');
  }

  login(){
    
    if (this.creds.username.length < 4){
      let alert = this.alertCtrl.create({
        title: "Usuário Inválido",
        message: "Por favor, digite um usuário válido",
        buttons:[{
          text: 'Ok'
        }]
      });
      alert.present();
      
      return;
    }
    if (this.creds.password.length < 4){
      let alert = this.alertCtrl.create({
        title: "Senha Inválida",
        message: "Por favor, digite uma senha válida",
        buttons:[{
          text: 'Ok'
        }]
      });
      alert.present();
      
      return;
    }
    console.log(this.creds);
  }

}

