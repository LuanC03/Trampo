import { Component } from '@angular/core';
import { IonicPage, NavController, MenuController } from 'ionic-angular';

import { DadosUsuarioDTO } from '../../models/dados-usuario.dto';

import { AutenticacaoService } from '../../services/autenticacao.service';
import { StorageService } from '../../services/storage.service';
import { EspecialidadesService } from '../../services/especialidades.service';

@IonicPage()
@Component({
  selector: 'page-requisicao',
  templateUrl: 'requisicao-servico.html',
})
export class RequisicaoServicoPage {

  user: string;
  especialidades : string[] = [];

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
    public autenticacaoService: AutenticacaoService,
    public storageService: StorageService,
    public menuCtrl: MenuController,
    public especialidadesService: EspecialidadesService)     {
      this.getEspecialidades();
  }

  ionViewDidLoad() {
    let localUser = this.storageService.getLocalUser();
    if (localUser && localUser.email){
      this.user = localUser.email;
    }
  }

  ionBackPage() {
    this.navCtrl.setRoot('HomePage');
  }

  getEspecialidades(){
    this.especialidadesService.getEspecialidades().subscribe(response => {
      for (var key in response.body){
        this.especialidades.push(response.body[key]['nome']);    
      }
    });      
  }

}
