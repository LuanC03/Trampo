import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import { DadosUsuarioDTO } from '../../models/dados-usuario.dto';
import { UsuarioService } from '../../services/usuario.service';
import { StorageService } from '../../services/storage.service';
import { EspecialidadesService } from '../../services/especialidades.service';
import { DadosAtualizadosDTO } from '../../models/dados-atualizados.dto';



@IonicPage()
@Component({
  selector: 'page-editar',
  templateUrl: 'edit-perfil.html',
})
export class EditPerfilPage {

  especialidades : string[] = [];

  dadosUsuario : DadosUsuarioDTO = {
    id: null,
    tipo: "",
    fotoPerfil : "",
    nomeCompleto : "",
    login : "",
    email : ""
  };

  dadosAtualizados : DadosAtualizadosDTO = {
    novafotoPerfil : "",
    novoNomeCompleto : "",
    novoLogin : "",
    novoEmail : "",
    novaEspecialidades : []
  }
  
  constructor(public navCtrl: NavController, 
    public navParams: NavParams,
    public usuarioService: UsuarioService,
    public storageService: StorageService,
    public especialidadesService: EspecialidadesService) {
  }

  ionViewDidLoad() {
    this.getEspecialidades();
    this.usuarioService.getMyUser().subscribe(
      response => {
        this.dadosUsuario = response['data'];
        this.dadosAtualizados.novaEspecialidades = this.dadosUsuario.listaEspecialidades;
        this.dadosAtualizados.novafotoPerfil = this.dadosUsuario.fotoPerfil;
        this.dadosAtualizados.novoNomeCompleto = this.dadosUsuario.nomeCompleto;
        this.dadosAtualizados.novoLogin = this.dadosUsuario.login;
        this.dadosAtualizados.novoEmail = this.dadosUsuario.email;
      }, error => {
        console.log(error);
      });
  }

  salvar() {
    console.log(this.dadosAtualizados);
    this.navCtrl.pop();
  }

  getEspecialidades(){
    this.especialidadesService.getEspecialidades().subscribe(response => {
      for (var key in response.body){
        this.especialidades.push(response.body[key]['nome']);    
      }
    });      
  }

}
