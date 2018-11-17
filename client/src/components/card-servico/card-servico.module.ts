import { IonicModule } from 'ionic-angular';
import { NgModule } from '@angular/core';

import { CardServicoComponent } from './card-servico';

@NgModule({
    declarations: [CardServicoComponent],
    imports: [IonicModule],
    exports: [CardServicoComponent]
})
export class CardServicoModule { }
