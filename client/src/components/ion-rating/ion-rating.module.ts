import { IonicModule } from 'ionic-angular';
import { NgModule } from '@angular/core';

import { IonRatingComponent } from './ion-rating';

@NgModule({
	declarations: [IonRatingComponent],
	imports: [IonicModule],
	exports: [IonRatingComponent]
})
export class IonRatingComponentModule {}
