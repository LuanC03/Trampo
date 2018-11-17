import { IonicModule } from 'ionic-angular';
import { NgModule } from '@angular/core';

import { CardServicoComponent } from './card-servico/card-servico';
import { IonRatingComponent } from './ion-rating/ion-rating';

@NgModule({
    declarations: [
        CardServicoComponent,
        IonRatingComponent
    ],
    imports: [IonicModule],
    exports: [
        CardServicoComponent,
        IonRatingComponent
    ]
})
export class ComponentsModule { }
