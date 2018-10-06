import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { API_CONFIG } from "../config/api.config";
import { CredenciaisDTO } from "../models/credenciais.dto";
import { LocalUser } from "../models/local_user";
import { StorageService } from "./storage.service";


@Injectable()
export class AutenticacaoService {

    constructor(public http: HttpClient,
        public storage: StorageService){
    }

    login(creds : CredenciaisDTO){
        return this.http.post(
            `${API_CONFIG.baseUrl}/api/login`,
            creds,
            {
                observe: 'response',
                responseType: 'json'
            });
    }

    successfulLogin(value : string){
        let user : LocalUser = {
            token: value
        }
        this.storage.setLocalUser(user);
    }

    logout(){
        this.storage.setLocalUser(null);
    }

    
}