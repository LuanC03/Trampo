import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { API_CONFIG } from "../config/api.config";
import { ServicoClienteDTO } from "../models/servico-cliente.dto";

@Injectable()
export class ServicoFornecedorService {

    constructor(public http: HttpClient){
    }

    getServicos(){
        return this.http.get(`${API_CONFIG.baseUrl}/api/servicos/fornecedor`,
        {
            observe: 'response',
            responseType: 'json'
        });
    }
}