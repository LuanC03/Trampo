import { DadosUsuarioDTO } from "./dados-usuario.dto";

export interface ServicoDTO {
    id: number,
    data: string,
    horario: string,
    valor: string,
    tipo: string,
    endereco: {
    rua: string,
    bairro: string,
    numero: string
    },
    fornecedor?: string,
    status?: string,
    cliente?: DadosUsuarioDTO
}
