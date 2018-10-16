import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { API_CONFIG } from "../config/api.config";
import { ServicoDTO } from "../models/servico.dto";

@Injectable()
export class ServicoClienteService {

    constructor(public http: HttpClient){
    }

    cadastraServicoCliente(dados: ServicoDTO){
        return this.http.post(`${API_CONFIG.baseUrl}/api/servicos/cliente`,
        dados,
        {
            observe: 'response',
            responseType: 'json'
        });

    }

    getServicos(){
        return this.http.get(`${API_CONFIG.baseUrl}/api/servicos/cliente`,
        {
            observe: 'response',
            responseType: 'json'
        });
    }
}