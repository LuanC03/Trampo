import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams, ViewController, AlertController } from 'ionic-angular';

import { AvaliacaoDTO } from '../../models/avaliacao-servico.dto';
import { AvaliacaoService } from '../../services/avaliacao.service';

//import { ServicoDTO } from '../../models/servico.dto';

import { ServicoFornecedorService } from '../../services/servico-fornecedor.service';
import { ServicoClienteService } from '../../services/servico-cliente.service';

import { UsuarioService } from '../../services/usuario.service';
import { DadosUsuarioDTO } from '../../models/dados-usuario.dto';


@IonicPage()
@Component({
  selector: 'page-avaliacao',
  templateUrl: 'avaliacao.html'
})
export class AvaliacaoPage {
  private rate: number = 0;
  user: DadosUsuarioDTO;

  avaliar: AvaliacaoDTO = {
    avaliacao: {
        id: null,
        nota: null
    },  
    servico: {
      id: null
    }
  };
  
/*
  avaliacao: AvaliacaoDTO = {
    id: null,
    nota: "",
    servico: {
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
    }
  };
*/
  constructor(
    public usuarioService: UsuarioService,
    public servicoFornecedorService: ServicoFornecedorService,
    public servicoClienteService: ServicoClienteService,

    public avaliacaoService: AvaliacaoService,
    public alertCtrl: AlertController,
    public viewCtrl: ViewController,
  	public navCtrl: NavController, 
  	public navParams: NavParams) {
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad AvaliacaoPage');
  }

  onRate(value) {
    this.rate = value;
  	this.setNota(this.rate);
  }

  private setNota(rate) {
  	this.avaliar.avaliacao.nota = rate ;
  }

  confirmar(avaliar: AvaliacaoDTO) {
  	//window.alert(avaliacao.id);
    //this.servicoClienteService.avaliacaoServico(avaliacao).subscribe(
    this.servicoFornecedorService.avaliacaoServico(avaliar).subscribe(
	
	//this.avaliacaoService.avaliacaoServico(avaliacao).subscribe(
      response => {
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
	
  }

}
