import { Component } from '@angular/core';
import { IonicPage, NavController, AlertController } from 'ionic-angular';

import { AutenticacaoService } from '../../services/autenticacao.service';
import { StorageService } from '../../services/storage.service';
import { NavParams } from 'ionic-angular/navigation/nav-params';
import { ServicoDTO } from '../../models/servico.dto';
import { DadosUsuarioLogadoDTO } from '../../models/dados-usuario-logado.dto';
import { UsuarioService } from '../../services/usuario.service';
import { ServicoFornecedorService } from '../../services/servico-fornecedor.service';


@IonicPage()
@Component({
  selector: 'page-detalhes',
  templateUrl: 'detalhe-servico.html',
})
export class DetalheServicoPage {

  nomeFornecedor: string;
  user: DadosUsuarioLogadoDTO;
  servico: ServicoDTO = {
    id: null,
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
    cliente: null
  };

  constructor(public navCtrl: NavController,
    public autenticacaoService: AutenticacaoService,
    public storageService: StorageService,
    public navParams: NavParams,
    public usuarioService: UsuarioService,
    public servicoFornecedorService: ServicoFornecedorService,
    public alertCtrl: AlertController )     {
  }

  ionViewDidLoad() {

    this.usuarioService.findByUsername(this.storageService.getLocalUser().username).subscribe(
      response => {
        this.user = response['data'];
        this.servico.id = this.navParams.get("id");
        this.servico.cliente = this.user.login;
        this.servico.tipo = this.navParams.get("tipo");
        this.servico.data = this.navParams.get("data");
        this.servico.horario = this.navParams.get("horario");
        this.servico.valor = this.navParams.get("valor");
        this.servico.status = this.navParams.get("status");
        this.servico.endereco = this.navParams.get("endereco");
        if (this.navParams.get("fornecedor")){
          this.servico.fornecedor = this.navParams.get("fornecedor");
          this.nomeFornecedor = this.servico.fornecedor['nomeCompleto']
        }
      });
  }

  ionBackPage(){
    this.navCtrl.pop();
  }

  cadastrarServico(){
    this.servico.cliente = null;
    console.log(this.servico);
    this.servicoFornecedorService.cadastrarServico(this.servico).subscribe(
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

}
