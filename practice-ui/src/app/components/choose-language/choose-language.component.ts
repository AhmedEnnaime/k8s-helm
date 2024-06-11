import { Component } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'app-choose-language',
  templateUrl: './choose-language.component.html',
  styleUrls: ['./choose-language.component.css'],
})
export class ChooseLanguageComponent {
  languages: string[] = ['en', 'ar'];
  constructor(private translate: TranslateService) {}
  setLanguage(event: any) {
    this.translate.use(event.target.value);
  }
}
