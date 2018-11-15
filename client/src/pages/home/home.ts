import { Component } from '@angular/core';
import { IonicPage, NavController, Events, NavParams } from 'ionic-angular';
import { LoginPage } from '../login/login';
import { AutenticacaoService } from '../../services/autenticacao.service';
import { StorageService } from '../../services/storage.service';
import { UsuarioService } from '../../services/usuario.service';
import { ServicoDTO } from '../../models/servico.dto';
import { ServicoClienteService } from '../../services/servico-cliente.service';
import { ServicoFornecedorService } from '../../services/servico-fornecedor.service';
import { DadosUsuarioDTO } from '../../models/dados-usuario.dto';

@IonicPage()
@Component({
  selector: 'page-home',
  templateUrl: 'home.html',
})
export class HomePage {

  user: string;
  tipo_usuario: string;

  dados_user: DadosUsuarioDTO = {
    id: null,
    tipo: "",
    fotoPerfil : "",
    nomeCompleto : "",
    login : "",
    email : "",
    avaliacao: null
  };
  servicos: ServicoDTO[];

  constructor(public navCtrl: NavController,
    public autenticacaoService: AutenticacaoService,
    public storageService: StorageService,
    public usuarioService: UsuarioService,
    public events: Events,
    public navParams: NavParams,
    public servicoClienteService: ServicoClienteService,
    public servicoFornecedorService: ServicoFornecedorService)     {
  }

  ionViewDidLoad() {
    let localUser = this.storageService.getLocalUser();
    if (localUser && localUser.username){
      this.user = localUser.username;
    }
    this.tipo_usuario = this.navParams.get("tipo");

    this.usuarioService.getMyUser().subscribe(
      response => {
        this.dados_user = response['data'];
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

  logout(){
    this.autenticacaoService.logout();
    this.navCtrl.setRoot(LoginPage);
  }

  openDetalhes(servico: ServicoDTO){
    this.navCtrl.push('DetalheServicoPage', servico)
  }
  
  log(valor){
    console.log(valor);
  }
}