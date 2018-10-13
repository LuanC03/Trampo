import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { API_CONFIG } from "../config/api.config";
import { DadosUsuarioDTO } from "../models/dados-usuario.dto";

@Injectable()
export class EspecialidadesService {

    constructor(public http: HttpClient){
    }

    getEspecialidades(){
        return this.http.get(`${API_CONFIG.baseUrl}/api/especialidade`, {
            observe: 'response',
            responseType: 'json'
        });
    }
}