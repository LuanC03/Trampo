import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { API_CONFIG } from "../config/api.config";
import { StorageService } from "./storage.service";


@Injectable()
export class UsuarioService {

    
    constructor(public http: HttpClient,
        public storage: StorageService){
    }

    findByUsername(username: string) {
        return this.http.get(`${API_CONFIG.baseUrl}/api/cliente/${username}`);
    }
  
}