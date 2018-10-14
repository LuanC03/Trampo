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
      // Okay, so the platform is ready and our plugins are available.
      // Here you can do any higher level native things you might need.
      statusBar.styleDefault();
      splashScreen.hide();
    });

    events.subscribe('user:cliente', () => {
      this.pages = [
        { title: 'Home', component: 'HomePage'},
        { title: 'Listagem de Serviço', component: 'ListagemServicoPage'},
        { title: 'Requisição de Serviço', component: 'RequisicaoServicoPage'}
      ];
    });

    events.subscribe('user:fornecedor', () => {
      this.pages = [
        { title: 'Home', component: 'HomePage'},
        { title: 'Listagem de Serviço', component: 'ListagemServicoFornecedorPage'},
        { title: 'Aceitar de Serviço', component: 'RequisicaoServicoPage'}
      ];
    });
  }

  openPage(page) {
    this.nav.setRoot(page.component);
  }
}

