import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { API_CONFIG } from "../config/api.config";
import { ServicoClienteDTO } from "../models/servico-cliente.dto";

@Injectable()
export class CadastroServClienteService {

    constructor(public http: HttpClient){
    }

    cadastraServico(dados: ServicoClienteDTO){
        return null;

    }
}