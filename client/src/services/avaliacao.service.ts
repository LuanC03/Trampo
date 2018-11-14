import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { API_CONFIG } from "../config/api.config";

import { AvaliacaoDTO } from '../models/avaliacao-servico.dto';
import { ServicoDTO } from "../models/servico.dto";

@Injectable()
export class AvaliacaoService {

    servico: any;

    constructor(public http: HttpClient){
    }

    avaliacaoServico(avaliacao : AvaliacaoDTO){
        return this.http.post(
            `${API_CONFIG.baseUrl}/api/usuarios/avaliacao/avaliar`,
            avaliacao,
            {
                observe: 'response',
                responseType: 'json'
            });
    }
}