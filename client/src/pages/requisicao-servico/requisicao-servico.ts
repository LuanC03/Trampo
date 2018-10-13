import { Component } from '@angular/core';
import { IonicPage, NavController, MenuController } from 'ionic-angular';

import { AutenticacaoService } from '../../services/autenticacao.service';
import { StorageService } from '../../services/storage.service';
import { EspecialidadesService } from '../../services/especialidades.service';
import { ServicoClienteDTO } from '../../models/servico-cliente.dto';

@IonicPage()
@Component({
  selector: 'page-requisicao',
  templateUrl: 'requisicao-servico.html',
})
export class RequisicaoServicoPage {

  user: string;
  especialidades : string[] = [];

  dados_servico : ServicoClienteDTO  = {
    data: "",
    horario: "",
    valor: "",
    tipo: "",
    endereco: {
    rua: "",
    bairro: "",
    numero: ""
    }
}



  constructor(public navCtrl: NavController,
    public autenticacaoService: AutenticacaoService,
    public storageService: StorageService,
    public menuCtrl: MenuController,
    public especialidadesService: EspecialidadesService)     {
      this.getEspecialidades();
  }

  ionViewDidLoad() {
    let localUser = this.storageService.getLocalUser();
    if (localUser && localUser.username){
      this.user = localUser.username.split(" ")[0];
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

  cadastrar(){
    console.log(this.dados_servico);
  }

}
