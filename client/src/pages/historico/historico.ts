import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import { ServicoDTO } from '../../models/servico.dto';
import { DadosUsuarioDTO } from '../../models/dados-usuario.dto';
import { UsuarioService } from '../../services/usuario.service';
import { ServicoClienteService } from '../../services/servico-cliente.service';
import { ServicoFornecedorService } from '../../services/servico-fornecedor.service';

@IonicPage()
@Component({
  selector: 'page-historico',
  templateUrl: 'historico.html',
})
export class HistoricoPage {

  user: DadosUsuarioDTO = {
    id: null,
    tipo: "",
    fotoPerfil : "",
    nomeCompleto : "",
    login : "",
    email : ""
  };
  servicos: ServicoDTO[];

  constructor(public navCtrl: NavController,
    public navParams: NavParams,
    public usuarioService: UsuarioService,
    public servicoClienteService: ServicoClienteService,
    public servicoFornecedorService: ServicoFornecedorService,
    ) {
  }

  ionViewDidLoad() {
    this.usuarioService.getMyUser().subscribe(
      response => {
        this.user = response['data'];
      }
    )
    this.usuarioService.getMyUser().subscribe(
      response => {
        if (response['data']['tipo'] == 'CLIENTE'){
          this.servicoClienteService.getHistorico().subscribe(
            response => {
              this.servicos = response.body['data'];
            });
        }else {
          this.servicoFornecedorService.getHistorico().subscribe(
            response => {
              this.servicos = response.body['data'];
            });   
        }
      }
    );    
  }

  openDetalhes(servico: ServicoDTO){
    this.navCtrl.push('DetalheServicoPage', servico)
  }

}
