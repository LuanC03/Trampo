import { NgModule } from '@angular/core';
import { IonicPageModule } from 'ionic-angular';
import { HomePage } from './home';
import { IonRatingComponentModule } from '../../components/ion-rating/ion-rating.module';

@NgModule({
  declarations: [
    HomePage,
  ],
  imports: [
    IonicPageModule.forChild(HomePage),
    IonRatingComponentModule
  ],
})
export class HomePageModule {}
