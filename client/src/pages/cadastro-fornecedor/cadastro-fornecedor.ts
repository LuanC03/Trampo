import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import { DadosFornecedorDTO } from '../../models/dados-fornecedor.dto';

/**
 * Generated class for the CadastroFornecedorPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'page-cadastro-fornecedor',
  templateUrl: 'cadastro-fornecedor.html',
})
export class CadastroFornecedorPage {

  especialidades : string[] = ["Chaveiro","Encanador", "Marceneiro", "Motorista","Pedreiro" ];
  
  dados_fornecedor : DadosFornecedorDTO = {
    foto : "",
    nome : "",
    username : "",
    email : "",
    senha : "",
    conf_senha : "",
    especialidades : []
  };

  constructor(public navCtrl: NavController, public navParams: NavParams) {

  }

  cadastrar(){
    console.log(this.dados_fornecedor);
  }


}
