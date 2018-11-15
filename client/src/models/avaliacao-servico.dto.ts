import { DadosUsuarioDTO } from "./dados-usuario.dto";

export interface AvaliacaoDTO {

    avaliacao: {
        id: number,
        nota: number
    },   
    servico: {
    	id: number,
    	descricao?: string,
    	data?: string,
    	horario?: string,
    	valor?: string,
    	tipo?: string,
    	endereco?: {
    		rua: string,
    		bairro: string,
    		numero: string
    	},
    	fornecedor?: string,
    	status?: string,
    	cliente?: DadosUsuarioDTO    
    }

}