import { ServicoDTO } from "./servico.dto";

export interface AvaliacaoDTO {
    id: number,
    nota: string,
    servico: ServicoDTO
}