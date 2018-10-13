import { BrowserModule } from '@angular/platform-browser';
import { ErrorHandler, NgModule } from '@angular/core';
//import { LocationStrategy, PathLocationStrategy } from '@angular/common';
import { IonicApp, IonicErrorHandler, IonicModule } from 'ionic-angular';
import { SplashScreen } from '@ionic-native/splash-screen';
import { StatusBar } from '@ionic-native/status-bar';

import { MyApp } from './app.component';
import { CadastroUsuarioService } from '../services/cadastro-usuario.service';
import { HttpClientModule } from '@angular/common/http';
import { AutenticacaoService } from '../services/autenticacao.service';
import { StorageService } from '../services/storage.service';
import { EspecialidadesService } from '../services/especialidades.service';
import { AuthInterceptorProvider } from '../interceptors/auth-interceptor';
import { UsuarioService } from '../services/usuario.service';
import { ServicoClienteService } from '../services/servico-cliente.service';

@NgModule({
  declarations: [
    MyApp
  ],
  imports: [
    BrowserModule,
    IonicModule.forRoot(MyApp),
    HttpClientModule
  ],
  bootstrap: [IonicApp],
  entryComponents: [
    MyApp
  ],
  providers: [
    StatusBar,
    SplashScreen,
    {provide: ErrorHandler, useClass: IonicErrorHandler},
    //{provide: LocationStrategy, useClass: PathLocationStrategy},
    CadastroUsuarioService,
    AutenticacaoService,
    StorageService,
    EspecialidadesService,
    AuthInterceptorProvider,
    ServicoClienteService,
    UsuarioService
  ]
})
export class AppModule {}
