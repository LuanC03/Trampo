export interface DadosCadastroUsuarioDTO {
    fotoPerfil : string;
    nomeCompleto : string;
    login : string;
    email : string;
    senha : string;
    conf_senha : string;
    listaEspecialidades? : string[];
}