import { Component, Input } from '@angular/core';
import { ServicoDTO } from '../../models/servico.dto';

@Component({
    selector: 'card-servico',
    templateUrl: 'card-servico.html'
})
export class CardServicoComponent {

    @Input('servico') servico: ServicoDTO;

    constructor() { }
}
