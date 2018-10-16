import { Component } from '@angular/core';
import { IonicPage, NavController } from 'ionic-angular';
import { DadosUsuarioLogadoDTO } from '../../models/dados-usuario-logado.dto';
import { ServicoDTO } from '../../models/servico.dto';
import { AutenticacaoService } from '../../services/autenticacao.service';
import { StorageService } from '../../services/storage.service';
import { ServicoClienteService } from '../../services/servico-cliente.service';
import { ServicoFornecedorService } from '../../services/servico-fornecedor.service';
import { UsuarioService } from '../../services/usuario.service';



@IonicPage()
@Component({
  selector: 'page-listagem-servico-aceitos',
  templateUrl: 'listagem-servico-aceitos.html',
})
export class ListagemServicoAceitosPage {
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
          this.servicoFornecedorService.getServicosAceitos().subscribe(
            response => {
              this.servicos = response.body['data'];
            });   
      }
    );
    
  }

  ionBackPage(){
    this.navCtrl.setRoot('HomePage');
  }

  openDetalhes(servico: ServicoDTO){
    this.navCtrl.push('DetalheServicoPage', servico)
  }

}
