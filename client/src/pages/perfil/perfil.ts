import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import { UsuarioService } from '../../services/usuario.service';
import { StorageService } from '../../services/storage.service';
import { DadosUsuarioDTO } from '../../models/dados-usuario.dto';

@IonicPage()
@Component({
  selector: 'page-perfil',
  templateUrl: 'perfil.html',
})
export class PerfilPage {

  dadosUsuario : DadosUsuarioDTO = {
      id: null,
      tipo: "",
      fotoPerfil : "",
      nomeCompleto : "",
      login : "",
      email : "",
      senha : "",
      conf_senha : "",
    };

  constructor(public navCtrl: NavController, 
    public navParams: NavParams,
    public usuarioService: UsuarioService,
    public storageService: StorageService) {

  }

  ionViewDidLoad() {
    this.usuarioService.findByUsername(this.storageService.getLocalUser().username).subscribe(
      response => {
        this.dadosUsuario = response['data'];
        console.log(this.dadosUsuario);
      }, error => {
        console.log(error);
      });
  }

  editar() {
  	
  }

}