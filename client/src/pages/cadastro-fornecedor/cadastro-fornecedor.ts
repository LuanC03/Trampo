import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams, AlertController } from 'ionic-angular';
import { DadosUsuarioDTO } from '../../models/dados-usuario.dto';
import { CadastroUsuarioService } from '../../services/cadastro-usuario.service';

@IonicPage()
@Component({
  selector: 'page-cadastro-fornecedor',
  templateUrl: 'cadastro-fornecedor.html',
})
export class CadastroFornecedorPage {

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
    public navParams: NavParams,
    public alertCtrl: AlertController,
    public cadastro: CadastroUsuarioService) {

  }

  cadastrar(){
    let alert : boolean = false;
    let myMessage : string = "";
    if (this.dados_fornecedor.nomeCompleto.length == 0){
      alert = true;
      myMessage += "*Nome inválido\n";
    }
    if (this.dados_fornecedor.login.length < 4){
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
    this.cadastro.cadastrar_fornecedor(this.dados_fornecedor)
    .subscribe(
      //this.navCtrl.setRoot('LoginPage');
    );
    console.log(this.dados_fornecedor);
  }


}
