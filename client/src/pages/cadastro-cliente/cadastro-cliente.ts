import { Component } from '@angular/core';
import { IonicPage, NavController, AlertController } from 'ionic-angular';
import { FormBuilder, Validators } from '@angular/forms';
import { CadastroUsuarioService } from '../../services/cadastro-usuario.service';
import { DadosUsuarioDTO } from '../../models/dados-usuario.dto';

@IonicPage()
@Component({
  selector: 'page-cadastro-cliente',
  templateUrl: 'cadastro-cliente.html',
})
export class CadastroClientePage {

  clienteForm = this.formBuilder.group({
    nome: ['', Validators.required, Validators.minLength(6)],
    nomeusuario: ['', Validators.required, Validators.minLength(4)],
    email: ['', Validators.required, Validators.minLength(6)],
    password: [],
    repassword: []
  })

  dados_cliente: DadosUsuarioDTO = {
    id: null,
    tipo: "",
    fotoPerfil: "",
    nomeCompleto: "",
    login: "",
    email: "",
    senha: "",
    conf_senha: ""
  };

  constructor(
    public navCtrl: NavController,
    public alertCtrl: AlertController,
    public cadastro: CadastroUsuarioService,
    private formBuilder: FormBuilder) {
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad CadastroClientePage');
  }

  cadastrar() {
    let alert: boolean = false;
    let myMessage: string = "";

    if (this.dados_cliente.nomeCompleto.length == 0) {
      alert = true;
      myMessage += "*Nome inválido\n";
    }

    if (this.dados_cliente.login.length < 4) {
      alert = true;
      myMessage += "*Username inválido\n"
    }

    if (this.dados_cliente.email.length < 3 && !this.dados_cliente.email.includes("@")) {
      alert = true;
      myMessage += "*Email inválido\n";
    }

    if (this.dados_cliente.senha.length < 4 || this.dados_cliente.senha != this.dados_cliente.conf_senha) {
      alert = true;
      myMessage += "*Senha inválida\n";
    }

    if (alert) {
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

    this.cadastro.cadastrar_cliente(this.dados_cliente).subscribe(response => {
      console.log(response.headers.get('Authorization'));
      let alertMessage = this.alertCtrl.create({
        title: "Cadastro efetuado com sucesso",
        message: "Bem vindo!",
        buttons: [{
          text: 'Ok'
        }]
      });
      alertMessage.present();
      this.navCtrl.setRoot('LoginPage');
    }, error => {
      console.log(error)
      let alertMessage = this.alertCtrl.create({
        title: "Problema no cadastro",
        message: error.error.message,
        buttons: [{
          text: 'Ok'
        }]
      });
      alertMessage.present();
    });
  }
}
