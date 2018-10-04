import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams, AlertController } from 'ionic-angular';
import { DadosUsuarioDTO } from '../../models/dados-usuario.dto';

@IonicPage()
@Component({
  selector: 'page-cadastro-fornecedor',
  templateUrl: 'cadastro-fornecedor.html',
})
export class CadastroFornecedorPage {

  especialidades : string[] = ["Chaveiro","Encanador", "Marceneiro", "Motorista","Pedreiro" ];
  
  dados_fornecedor : DadosUsuarioDTO = {
    foto : "",
    nome : "",
    username : "",
    email : "",
    senha : "",
    conf_senha : "",
    especialidades : []
  };

  constructor(public navCtrl: NavController, public navParams: NavParams, public alertCtrl: AlertController) {

  }

  cadastrar(){
    let alert : boolean = false;
    let myMessage : string = "";
    if (this.dados_fornecedor.nome.length == 0){
      alert = true;
      myMessage += "*Nome inválido\n";
    }
    if (this.dados_fornecedor.username.length < 4){
      alert = true;
      myMessage += "*Username inválido\n"
    }
    if (this.dados_fornecedor.email.length < 3 && !this.dados_fornecedor.email.includes("@")){
      alert = true;
      myMessage += "*Email inválido\n";
    }
    if (this.dados_fornecedor.senha.length < 4 || this.dados_fornecedor.senha != this.dados_fornecedor.conf_senha){
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
    console.log(this.dados_fornecedor);
  }


}
