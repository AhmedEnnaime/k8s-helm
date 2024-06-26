import { APP_INITIALIZER, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ApiModule } from 'client/src/app/core/modules/openapi';
import { HomeComponent } from './pages/home/home.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MainLayoutComponent } from './shared/main-layout/main-layout.component';
import {
  HTTP_INTERCEPTORS,
  HttpClient,
  HttpClientModule,
} from '@angular/common/http';
import { TranslateLoader, TranslateModule } from '@ngx-translate/core';
import { HttpLoaderFactory } from './utils/http-loader-factory';
import { ChooseLanguageComponent } from './components/choose-language/choose-language.component';
import { EmployeesTableComponent } from './components/employees-table/employees-table.component';
import { MatButtonModule } from '@angular/material/button';
import { MatTableModule } from '@angular/material/table';
import { MatSelectModule } from '@angular/material/select';
import { FormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { KeycloakService } from './services/keycloak.service';
import { LoginComponent } from './components/login/login.component';
import { HttpTokenInterceptor } from './services/interceptors/http-token.interceptor';

export function kcFactory(kcService: KeycloakService) {
  return () => kcService.init();
}
@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    MainLayoutComponent,
    ChooseLanguageComponent,
    EmployeesTableComponent,
    LoginComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ApiModule,
    HttpClientModule,
    MatButtonModule,
    MatSelectModule,
    MatFormFieldModule,
    MatInputModule,
    FormsModule,
    MatTableModule,
    BrowserAnimationsModule,
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: HttpLoaderFactory,
        deps: [HttpClient],
      },
      defaultLanguage: 'en',
    }),
  ],
  providers: [
    HttpClient,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: HttpTokenInterceptor,
      multi: true,
    },
    {
      provide: APP_INITIALIZER,
      deps: [KeycloakService],
      useFactory: kcFactory,
      multi: true,
    },
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
