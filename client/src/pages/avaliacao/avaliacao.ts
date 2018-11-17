import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams, ViewController, AlertController } from 'ionic-angular';
import { ServicoDTO } from '../../models/servico.dto';
import { AvaliacaoDTO } from '../../models/avaliacao-servico.dto';
import { AvaliacaoService } from '../../services/avaliacao.service';

@IonicPage()
@Component({
    selector: 'page-avaliacao',
    templateUrl: 'avaliacao.html'
})
export class AvaliacaoPage {

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
        tipoStatus: "",
        cliente: {
            id: null,
            login: "",
            nomeCompleto: "",
            tipo: "",
            fotoPerfil: "",
            email: "",
            avaliacao: null
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
        public alertCtrl: AlertController,
        public viewCtrl: ViewController,
        public navCtrl: NavController,
        public navParams: NavParams) {
        this.servico = navParams.get('servico');
    }

    ionViewDidLoad() {
        console.log('ionViewDidLoad AvaliacaoPage');
    }

    confirmarAvaliacao(avaliar: AvaliacaoDTO) {
        avaliar.servico = this.servico;

        this.avaliacaoService.avaliacaoServico(avaliar).subscribe(
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
    }
}
