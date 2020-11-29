import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { QuestionnaireSharedModule } from 'app/shared/shared.module';
import { HOME_ROUTE } from './home.route';
import { HomeComponent } from './home.component';

@NgModule({
  imports: [QuestionnaireSharedModule, RouterModule.forChild([HOME_ROUTE])],
  declarations: [HomeComponent],
})
export class QuestionnaireHomeModule {}
