import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { API_CONFIG } from "../config/api.config";
import { ServicoDTO } from "../models/servico.dto";

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

    getServicosAceitos(){
        return this.http.get(`${API_CONFIG.baseUrl}/api/servicos/fornecedor/aceitos`,
        {
            observe: 'response',
            responseType: 'json'
        });
    }

    cadastrarServico(servico: ServicoDTO){
        return this.http.post(`${API_CONFIG.baseUrl}/api/servicos/fornecedor`,
        servico,
        {
            observe: 'response',
            responseType: 'json'
        });
    }
}