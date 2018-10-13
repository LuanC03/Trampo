import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { API_CONFIG } from "../config/api.config";
import { DadosCadastroUsuarioDTO } from "../models/dados-cadastro-usuario.dto";

@Injectable()
export class CadastroUsuarioService {

    constructor(public http: HttpClient){
    }

    cadastrar_cliente(dados : DadosCadastroUsuarioDTO){
        return this.http.post(
            `${API_CONFIG.baseUrl}/api/cliente`,
            dados,
            {
                observe: 'response',
                responseType: 'json'
            });
        
    }

    cadastrar_fornecedor(dados : DadosCadastroUsuarioDTO){
        return this.http.post(
            `${API_CONFIG.baseUrl}/api/fornecedor`,
            dados,
            {
                observe: 'response',
                responseType: 'json'
            });
            
        
    }
}