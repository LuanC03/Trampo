import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams, ViewController } from 'ionic-angular';
import { senhaAtualizadaDTO } from '../../models/senha-atualizada.dto';
import { UsuarioService } from '../../services/usuario.service';

@IonicPage()
@Component({
  selector: 'page-atualiza-senha',
  templateUrl: 'atualiza-senha.html',
})
export class AtualizaSenhaPage {

  dadosSenha : senhaAtualizadaDTO = {
    senhaAntiga : "",
    senhaNova : "",
    confirmacao : ""
  };

  constructor(public navCtrl: NavController, 
    public navParams: NavParams,
    public viewCtrl: ViewController,
    public usuarioService: UsuarioService) {
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad AtualizaSenhaPage');
  }

  atualizar(dadosSenha : senhaAtualizadaDTO){
    this.usuarioService.atualizaSenha(dadosSenha).subscribe(
      response => {
        console.log(response);
        this.viewCtrl.dismiss();
      }, error => {
        console.log(error);
      });
  }

  cancelar() {
    this.viewCtrl.dismiss();
  }

}
