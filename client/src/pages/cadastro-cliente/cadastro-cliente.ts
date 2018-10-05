import { Component } from '@angular/core';
import { IonicPage, NavController, AlertController } from 'ionic-angular';
import { DadosUsuarioDTO } from '../../models/dados-usuario.dto';
import { CadastroUsuarioService } from '../../services/cadastro-usuario.service';

@IonicPage()
@Component({
  selector: 'page-cadastro-cliente',
  templateUrl: 'cadastro-cliente.html',
})
export class CadastroClientePage {

  dados_cliente : DadosUsuarioDTO = {
    fotoPerfil : "",
    nomeCompleto : "",
    login : "",
    email : "",
    senha : "",
    conf_senha : "",
    especialidades : []
  };

  constructor(public navCtrl: NavController, 
    public alertCtrl: AlertController,
    public cadastro: CadastroUsuarioService) {
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad CadastroClientePage');
  }

  cadastrar(){
    let alert : boolean = false;
    let myMessage : string = "";
    if (this.dados_cliente.nomeCompleto.length == 0){
      alert = true;
      myMessage += "*Nome inválido\n";
    }
    if (this.dados_cliente.login.length < 4){
      alert = true;
      myMessage += "*Username inválido\n"
    }
    if (this.dados_cliente.email.length < 3 && !this.dados_cliente.email.includes("@")){
      alert = true;
      myMessage += "*Email inválido\n";
    }
    if (this.dados_cliente.senha.length < 4 || this.dados_cliente.senha != this.dados_cliente.conf_senha){
      alert = true;
      myMessage += "*Senha inválida\n";
    }
    if (alert){
      let alertMessage = this.alertCtrl.create({
        title: "Problemas no cadastro",
        message: myMessage,
        buttons: [{
          text: 'Ok'
        }]
      });
      alertMessage.present();
      return;
    }
    this.cadastro.cadastrar(this.dados_cliente)
    .subscribe(
      //this.navCtrl.setRoot('LoginPage');
    );
    console.log(this.dados_cliente);
  }

}
