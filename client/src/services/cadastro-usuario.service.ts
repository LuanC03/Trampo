import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { API_CONFIG } from "../config/api.config";
import { DadosUsuarioDTO } from "../models/dados-usuario.dto";

@Injectable()
export class CadastroUsuarioService {

    constructor(public http: HttpClient){
    }

    cadastrar_cliente(dados : DadosUsuarioDTO){
        return this.http.post(
            `${API_CONFIG.baseUrl}/api/cliente`,
            dados);
        
    }

    cadastrar_fornecedor(dados : DadosUsuarioDTO){
        return this.http.post(
            `${API_CONFIG.baseUrl}/api/fornecedor`,
            dados);
        
    }
}