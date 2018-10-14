import { Component, ViewChild } from '@angular/core';
import { Platform, Nav, Events } from 'ionic-angular';
import { StatusBar } from '@ionic-native/status-bar';
import { SplashScreen } from '@ionic-native/splash-screen';

@Component({
  templateUrl: 'app.html'
})
export class MyApp {
  @ViewChild(Nav) nav: Nav;

  rootPage: string = 'LoginPage';

  pages: Array<{title: string, component: string}>;

  constructor(platform: Platform, 
    statusBar: StatusBar,
    splashScreen: SplashScreen,
    events: Events) {
    platform.ready().then(() => {
      statusBar.styleDefault();
      splashScreen.hide();
    });

    events.subscribe('user:CLIENTE', () => {
      this.pages = [
        { title: 'Home', component: 'HomePage'},
        { title: 'Listagem de Serviço', component: 'ListagemServicoPage'},
        { title: 'Requisição de Serviço', component: 'RequisicaoServicoPage'}
      ];
    });

    events.subscribe('user:FORNECEDOR', () => {
      this.pages = [
        { title: 'Home', component: 'HomePage'},
        { title: 'Serviços Disponíveis', component: 'ListagemServicoPage'},
        { title: 'Meus Serviços', component: 'ListagemServicoPage'}
      ];
    });
  }

  openPage(page) {
    this.nav.setRoot(page.component);
  }
}

