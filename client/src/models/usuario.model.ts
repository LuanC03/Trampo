export class Usuario {
    public id: number;
    public nomeCompleto: string;
    public login: string;
    public fotoPerfil: string;
    public email: string;
    public senha: string;
    public tipo: string;

    constructor(id?: number, nomeCompleto?: string, login?: string, fotoPerfil?: string,
        email?: string, senha?: string, tipo?: string) {
            this.id = id;
            this.nomeCompleto = nomeCompleto;
            this.login = login;
            this.fotoPerfil = fotoPerfil;
            this.email = email;
            this.senha = senha;
            this.tipo = tipo;
    }
}