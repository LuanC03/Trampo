import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import { DadosUsuarioDTO } from '../../models/dados-usuario.dto';
import { UsuarioService } from '../../services/usuario.service';
import { StorageService } from '../../services/storage.service';



@IonicPage()
@Component({
  selector: 'page-edit-perfil',
  templateUrl: 'edit-perfil.html',
})
export class EditPerfilPage {

  dadosUsuario : DadosUsuarioDTO = {
    id: null,
    tipo: "",
    fotoPerfil : "",
    nomeCompleto : "",
    login : "",
    email : ""
  };
  
  constructor(public navCtrl: NavController, 
    public navParams: NavParams,
    public usuarioService: UsuarioService,
    public storageService: StorageService) {
  }

  ionViewDidLoad() {
    this.usuarioService.getMyUser().subscribe(
      response => {
        this.dadosUsuario = response['data'];
        console.log(this.dadosUsuario);
      }, error => {
        console.log(error);
      });
  }

  salvar() {
    console.log(this.dadosUsuario);
    this.navCtrl.pop();
  }

}
