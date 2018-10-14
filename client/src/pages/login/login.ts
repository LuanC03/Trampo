import { Component } from '@angular/core';
import { NavController, LoadingController, AlertController, IonicPage, Events } from 'ionic-angular';
import { CredenciaisDTO } from '../../models/credenciais.dto';
import { AutenticacaoService } from '../../services/autenticacao.service';
import { StorageService } from '../../services/storage.service';
import { DadosUsuarioLogadoDTO } from '../../models/dados-usuario-logado.dto';
import { UsuarioService } from '../../services/usuario.service';

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

  dadosUsuario : DadosUsuarioLogadoDTO;

  constructor(public navCtrl: NavController, 
    private alertCtrl: AlertController,
    public loadingCrtl: LoadingController,
    public autenticacao: AutenticacaoService,
    public storage: StorageService,
    public usuario: UsuarioService,
    public events: Events) {
    
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
        duration: 1000
      });
      loading.present();
      this.usuario.findByUsername(this.storage.getLocalUser().username).subscribe(
        response => {
          this.dadosUsuario = response['data'];
          if (this.dadosUsuario.tipo == 'CLIENTE'){
            this.events.publish('user:cliente');
          }else if (this.dadosUsuario.tipo == 'FORNECEDOR'){
            this.events.publish('user:fornecedor');
          }
          this.navCtrl.setRoot('HomePage', this.dadosUsuario);
        }
      )
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
