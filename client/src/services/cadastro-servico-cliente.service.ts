import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { API_CONFIG } from "../config/api.config";
import { ServicoClienteDTO } from "../models/servico-cliente.dto";

@Injectable()
export class CadastroServService {

    constructor(public http: HttpClient){
    }

    cadastraServicoCliente(dados: ServicoClienteDTO){
        return this.http.post(`${API_CONFIG.baseUrl}/api/servicos/cliente`,
        dados,
        {
            observe: 'response',
            responseType: 'json'
        });

    }
}