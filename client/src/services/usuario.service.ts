import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { API_CONFIG } from "../config/api.config";
import { CredenciaisDTO } from "../models/credenciais.dto";
import { LocalUser } from "../models/local-user";
import { StorageService } from "./storage.service";
import { DadosUsuarioDTO } from "../models/dados-usuario.dto";


@Injectable()
export class AutenticacaoService {

    
    constructor(public http: HttpClient){
    }

    findByUsername(username: string) : DadosUsuarioDTO {
        return null;
    }
    
}