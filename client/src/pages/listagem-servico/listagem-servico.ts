import { Component } from '@angular/core';
import { IonicPage, NavController } from 'ionic-angular';

import { AutenticacaoService } from '../../services/autenticacao.service';
import { StorageService } from '../../services/storage.service';
import { ServicoClienteService } from '../../services/servico-cliente.service';
import { ServicoDTO } from '../../models/servico.dto';
import { ServicoFornecedorService } from '../../services/servico-fornecedor.service';
import { UsuarioService } from '../../services/usuario.service';
import { DadosUsuarioLogadoDTO } from '../../models/dados-usuario-logado.dto';


@IonicPage()
@Component({
  selector: 'page-listagem',
  templateUrl: 'listagem-servico.html',
})
export class ListagemServicoPage {

  user: DadosUsuarioLogadoDTO;
  servicos: ServicoDTO[];
  

  constructor(public navCtrl: NavController,
    public autenticacaoService: AutenticacaoService,
    public storageService: StorageService,
    public servicoClienteService: ServicoClienteService,
    public servicoFornecedorService: ServicoFornecedorService,
    public usuarioService: UsuarioService)     {

  }

  ionViewDidLoad() {
    this.usuarioService.findByUsername(this.storageService.getLocalUser().username).subscribe(
      response => {
        if (response['data']['tipo'] == 'CLIENTE'){
          this.servicoClienteService.getServicos().subscribe(
            response => {
              this.servicos = response.body['data'];
            });
        }else {
          this.servicoFornecedorService.getServicos().subscribe(
            response => {
              this.servicos = response.body['data'];
            });   
        }
      }
    )
  }

  ionBackPage(){
    this.navCtrl.setRoot('HomePage');
  }

  openDetalhes(servico: ServicoDTO){
    this.navCtrl.push('DetalheServicoPage', servico)
  }

}
