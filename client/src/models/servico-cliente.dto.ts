export interface ServicoClienteDTO {
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
    cliente?: string
}
