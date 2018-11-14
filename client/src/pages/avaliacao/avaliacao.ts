import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams, ViewController, AlertController } from 'ionic-angular';

import { AvaliacaoDTO } from '../../models/avaliacao-servico.dto';
import { AvaliacaoService } from '../../services/avaliacao.service';

import { ServicoFornecedorService } from '../../services/servico-fornecedor.service';
import { UsuarioService } from '../../services/usuario.service';


@IonicPage()
@Component({
  selector: 'page-avaliacao',
  templateUrl: 'avaliacao.html'
})
export class AvaliacaoPage {
  private rate: number = 0;

  avaliacao : AvaliacaoDTO = {
    id: null,
    nota: "",
    servico: null
  };

   constructor(
    public usuarioService: UsuarioService,
    public servicoFornecedorService: ServicoFornecedorService,
    public avaliacaoService: AvaliacaoService,

    public alertCtrl: AlertController,
    public viewCtrl: ViewController,
  	public navCtrl: NavController, 
  	public navParams: NavParams) {
   	console.log(navParams.get('servico'));
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad AvaliacaoPage');
  }

  onRate(value) {
    this.rate = value * 20;
  	this.setNota(this.rate);
  }

  private setNota(rate) {
  	this.avaliacao.nota = rate + "";
  }

  confirmar() {
  	//window.alert(this.avaliacao.nota);
    //this.servicoFornecedorService.avaliacaoServico(this.avaliacao).subscribe(
	this.avaliacaoService.avaliacaoServico(this.avaliacao).subscribe(
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
