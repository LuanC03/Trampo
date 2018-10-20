import { Component } from '@angular/core';
import { IonicPage, NavController, AlertController } from 'ionic-angular';

import { AutenticacaoService } from '../../services/autenticacao.service';
import { StorageService } from '../../services/storage.service';
import { EspecialidadesService } from '../../services/especialidades.service';
import { ServicoDTO } from '../../models/servico.dto';
import { ServicoClienteService } from '../../services/servico-cliente.service';

@IonicPage()
@Component({
  selector: 'page-requisicao',
  templateUrl: 'requisicao-servico.html',
})
export class RequisicaoServicoPage {

  user: string;
  especialidades : string[] = [];

  dados_servico : ServicoDTO  = {
    id: null,
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
    public especialidadesService: EspecialidadesService,
    public cadastroServService: ServicoClienteService,
    public alertCtrl: AlertController)     {
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
    this.cadastroServService.cadastraServicoCliente(this.dados_servico).subscribe(
      response => {
        console.log()
        let alertMessage = this.alertCtrl.create({
          message: response.body['message'],
          buttons: [{
            text: 'Ok'
          }]
        });
        alertMessage.present();
        this.navCtrl.setRoot('HomePage');
      },
      error => {
        console.log(error);
        let alertMessage = this.alertCtrl.create({
          message: error.error['message'],
          buttons: [{
            text: 'Ok'
          }]
        });
        alertMessage.present();
      }
    );
    
  }

}