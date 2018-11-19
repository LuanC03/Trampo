import { Component } from '@angular/core';
import { IonicPage, NavController, ViewController, ModalController, AlertController } from 'ionic-angular';
import { NavParams } from 'ionic-angular/navigation/nav-params';

import { AutenticacaoService } from '../../services/autenticacao.service';
import { StorageService } from '../../services/storage.service';
import { UsuarioService } from '../../services/usuario.service';
import { ServicoFornecedorService } from '../../services/servico-fornecedor.service';
import { ServicoClienteService } from '../../services/servico-cliente.service';


import { ServicoDTO } from '../../models/servico.dto';
import { DadosUsuarioDTO } from '../../models/dados-usuario.dto';
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
        tipoStatus: "",
        fornecedor: {
            id: null,
            login: "",
            nomeCompleto: "",
            tipo: "",
            fotoPerfil: "",
            email: "",
            avaliacao: null
        },
        cliente: {
            id: null,
            login: "",
            nomeCompleto: "",
            tipo: "",
            fotoPerfil: "",
            email: "",
            avaliacao: null
        },
        isAvaliadoCliente: null,
        isAvaliadoFornecedor: null
    };

    avaliar: AvaliacaoDTO = {
        avaliacao: {
            id: null,
            nota: null
        },
        servico: this.servico
    };

    constructor(
        public viewCtrl: ViewController,
        public alertCtrl: AlertController,
        public navParams: NavParams,
        public navCtrl: NavController,
        public modalCtrl: ModalController,
        public usuarioService: UsuarioService,
        public storageService: StorageService,
        public avaliacaoService: AvaliacaoService,
        public autenticacaoService: AutenticacaoService,
        public servicoClienteService: ServicoClienteService,
        public servicoFornecedorService: ServicoFornecedorService) { }


    ionViewDidLoad() {
        this.usuarioService.getMyUser().subscribe(
            response => {
                this.user = response['data'];
                this.servico = this.navParams['data'];

                if(this.user.tipo == 'FORNECEDOR') {
                    if (this.servico.tipoStatus == 'CONCLUIDO' && !this.servico.isAvaliadoFornecedor) {
                        this.avaliarServico();
                    }
                } else {
                    if (this.servico.tipoStatus == 'CONCLUIDO' && !this.servico.isAvaliadoCliente) {
                            this.avaliarServico();
                    }
                }
                console.log(this.servico);
            });

    }

    ionBackPage() {
        this.navCtrl.pop();
    }

    cadastrarServico(servico: ServicoDTO) {
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
            }, error => {
                let alertMessage = this.alertCtrl.create({
                    message: error.error['message'],
                    buttons: [{
                        text: 'Ok'
                    }]
                });
                alertMessage.present();
            });
    }

    concluirServico(servico: ServicoDTO) {
        this.servicoFornecedorService.concluirServico(servico).subscribe(
            response => {
                let alertMessage = this.alertCtrl.create({
                    message: response.body['message'],
                    buttons: [{
                        text: 'Ok'
                    }]
                });
                alertMessage.present();
                this.avaliarServico();
            }, error => {
                let alertMessage = this.alertCtrl.create({
                    message: error.error['message'],
                    buttons: [{
                        text: 'Ok'
                    }]
                });
                alertMessage.present();
            });
    }



    cancelaServicoCliente(servico: ServicoDTO) {
        this.servicoClienteService.cancelaServico(servico).subscribe(
            response => {
                let alertMessage = this.alertCtrl.create({
                    message: response.body['message'],
                    buttons: [{
                        text: 'Ok'
                    }]
                });
                alertMessage.present();
                this.navCtrl.setRoot("HomePage");
            }, error => {
                let alertMessage = this.alertCtrl.create({
                    message: error.error['message'],
                    buttons: [{
                        text: 'Ok'
                    }]
                });
                alertMessage.present();
                this.navCtrl.setRoot("HomePage");
            }
        )
    }

    avaliarServico() {         
        this.navCtrl.push('AvaliacaoPage', this.servico);

        if(this.user.tipo == 'FORNECEDOR') {                 
            this.servico.isAvaliadoFornecedor = true;                
        } else {
            this.servico.isAvaliadoCliente = true;
        }
    }
}