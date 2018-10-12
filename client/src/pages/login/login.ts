import { Component } from '@angular/core';
import { NavController, LoadingController, AlertController, IonicPage } from 'ionic-angular';
import { CredenciaisDTO } from '../../models/credenciais.dto';
import { AutenticacaoService } from '../../services/autenticacao.service';
import { StorageService } from '../../services/storage.service';

@IonicPage()
@Component({
  selector: 'page-login',
  templateUrl: 'login.html'
})
export class LoginPage {

  creds : CredenciaisDTO = {
    login : "",
    senha : ""
  };

  constructor(public navCtrl: NavController, 
    private alertCtrl: AlertController,
    public loadingCrtl: LoadingController,
    public autenticacao: AutenticacaoService,
    public storage: StorageService) {
    
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
    
    if (this.creds.login.length < 4){
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
    if (this.creds.senha.length < 4){
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
    this.autenticacao.login(this.creds).subscribe(response =>{
      this.autenticacao.successfulLogin(response.body["data"]["token"]);
      let loading = this.loadingCrtl.create({
        spinner: 'circles',
        content: 'Entrando',
        duration: 2000
      });
      loading.present();
      this.navCtrl.setRoot('HomePage');
    },
    error => {
      let alertMessage = this.alertCtrl.create({
        title: "Problema no Login",
        message: error.error.message,
        buttons: [{
          text: 'Ok'
        }]
      });
      alertMessage.present();
    });


  }

}
