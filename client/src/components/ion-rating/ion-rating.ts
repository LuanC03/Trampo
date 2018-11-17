import { Component, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'ion-rating',
  templateUrl: 'ion-rating.html'
})
export class IonRatingComponent {

  @Input() numStars: number = 5;
  @Input() value: number = 3;
  @Input() edit: boolean = false;

  @Output() ionClick: EventEmitter<number> = new EventEmitter<number>();
  stars: string[] = [];

  constructor(){}

  ngAfterViewInit(){
    this.reloadStars();
  }
  reloadStars(){
    this.stars = [];
    let tmp = this.value;
    for (let i=0; i < this.numStars; i++, tmp--){
      if (tmp >= 1){
        this.stars.push("star");
      }else{
        this.stars.push("star-outline");
      }
    }
  }

  starClicked(i: number){
    if (this.edit){
      this.value = i + 1;
      this.ionClick.emit(this.value);
      this.reloadStars();
    }
    
  }


}
