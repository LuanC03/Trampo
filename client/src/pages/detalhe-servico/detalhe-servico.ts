import { Component } from '@angular/core';
import { IonicPage, NavController, ModalController, AlertController } from 'ionic-angular';

import { AutenticacaoService } from '../../services/autenticacao.service';
import { StorageService } from '../../services/storage.service';
import { NavParams } from 'ionic-angular/navigation/nav-params';
import { ServicoDTO } from '../../models/servico.dto';
import { UsuarioService } from '../../services/usuario.service';
import { ServicoFornecedorService } from '../../services/servico-fornecedor.service';
import { DadosUsuarioDTO } from '../../models/dados-usuario.dto';

@IonicPage()
@Component({
  selector: 'page-detalhes',
  templateUrl: 'detalhe-servico.html',
})
export class DetalheServicoPage {

  nomeFornecedor: string;
  user: DadosUsuarioDTO;
  servico: ServicoDTO = {
    id: null,
    descricao: "",
    data: "",
    horario: "",
    valor: "",
    tipo: "",
    endereco: {
    rua: "",
    bairro: "",
    numero: ""
    },
    fornecedor: null,
    status: "",
    cliente: {
      id: null,
      login: "",
      nomeCompleto: "",
      tipo: "",
      fotoPerfil: "",
      email: "",
    }
  };

  constructor(
    public navCtrl: NavController,
    public modalCtrl: ModalController, 
    public autenticacaoService: AutenticacaoService,
    public storageService: StorageService,
    public navParams: NavParams,
    public usuarioService: UsuarioService,
    public servicoFornecedorService: ServicoFornecedorService,
    public alertCtrl: AlertController )     {
  }

  ionViewDidLoad() {
    this.usuarioService.getMyUser().subscribe(
      response => {
        this.user = response['data'];
        this.servico = this.navParams['data'];
        console.log(this.servico);
      });
  }

  ionBackPage(){
    this.navCtrl.pop();
  }

  cadastrarServico(servico: ServicoDTO){
    this.servicoFornecedorService.cadastrarServico(servico).subscribe(
      response => {
        let alertMessage = this.alertCtrl.create({
          message: response.body['message'],
          buttons: [{
            text: 'Ok'
          }]
        });
        alertMessage.present();
        this.navCtrl.setRoot('ListagemServicoPage');
      },error => {
        let alertMessage = this.alertCtrl.create({
          message: error.error['message'],
          buttons: [{
            text: 'Ok'
          }]
        });
        alertMessage.present();
      });
  }
  
  concluirServico(servico: ServicoDTO){
    this.avaliar(servico);
    return;
    this.servicoFornecedorService.concluirServico(servico).subscribe(
      response => {
        let alertMessage = this.alertCtrl.create({
          message: response.body['message'],
          buttons: [{
            text: 'Ok'
          }]
        });
        alertMessage.present();
        this.navCtrl.setRoot('ListagemServicoPage');
      },error => {
        let alertMessage = this.alertCtrl.create({
          message: error.error['message'],
          buttons: [{
            text: 'Ok'
          }]
        });
        alertMessage.present();
      });
  }

  private avaliar(servico: ServicoDTO) {
    let avaliacaoModal = this.modalCtrl.create('AvaliacaoPage', {servico: servico});
    avaliacaoModal.present();
  }

}
