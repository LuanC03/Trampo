import { Component } from '@angular/core';
import { IonicPage, NavController, ViewController, ModalController, AlertController } from 'ionic-angular';

import { AutenticacaoService } from '../../services/autenticacao.service';
import { StorageService } from '../../services/storage.service';
import { NavParams } from 'ionic-angular/navigation/nav-params';
import { ServicoDTO } from '../../models/servico.dto';
import { UsuarioService } from '../../services/usuario.service';
import { ServicoFornecedorService } from '../../services/servico-fornecedor.service';
import { DadosUsuarioDTO } from '../../models/dados-usuario.dto';


import { ServicoClienteService } from '../../services/servico-cliente.service';

import { AvaliacaoDTO } from '../../models/avaliacao-servico.dto';
import { AvaliacaoService } from '../../services/avaliacao.service';


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

  avaliar: AvaliacaoDTO = {
    avaliacao: {
        id: null,
        nota: null
    },  
    servico: this.servico
  };

  constructor(
    public avaliacaoService: AvaliacaoService,
    public viewCtrl: ViewController,
    public servicoClienteService: ServicoClienteService,

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
        
        //this.avaliar = this.navParams['data'];
        this.avaliar.servico = this.servico;

        console.log(this.servico);
      });
    window.alert(this.avaliar.avaliacao.nota);
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
  
  //concluirServico(servico: ServicoDTO){
  concluirServico(avaliar: AvaliacaoDTO){

    //window.alert(avaliacao.nota);
    this.avaliarServico(avaliar);

    /*
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
      */
  }

  avaliarServico(avaliar: AvaliacaoDTO) {
        //window.alert(this.servico.id);
        //avaliacao.servico.id = this.servico.id;

    avaliar.avaliacao.nota = 1;

  this.servicoFornecedorService.avaliacaoServico(avaliar).subscribe(
  //this.servicoClienteService.avaliacaoServico(avaliar).subscribe(
  //this.avaliacaoService.avaliacaoServico(avaliar).subscribe(
      response => {
        let alertMessage = this.alertCtrl.create({
          message: response.body['message'],
          buttons: [{
            text: 'Ok'
          }]
        });
        alertMessage.present();
        this.viewCtrl.dismiss();
      }, error => {
        let alertMessage = this.alertCtrl.create({
          message: error.error['message'],
          buttons: [{
            text: 'Ok'
          }]
        });
        alertMessage.present();
        this.viewCtrl.dismiss();
      });
//    let avaliacaoModal = this.modalCtrl.create('AvaliacaoPage');
  //  avaliacaoModal.present();
  }

}
