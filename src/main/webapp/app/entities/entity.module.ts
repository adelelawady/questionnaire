import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'application-user',
        loadChildren: () => import('./application-user/application-user.module').then(m => m.QuestionnaireApplicationUserModule),
      },
      {
        path: 'subject',
        loadChildren: () => import('./subject/subject.module').then(m => m.QuestionnaireSubjectModule),
      },
      {
        path: 'question',
        loadChildren: () => import('./question/question.module').then(m => m.QuestionnaireQuestionModule),
      },
      {
        path: 'answer',
        loadChildren: () => import('./answer/answer.module').then(m => m.QuestionnaireAnswerModule),
      },
      {
        path: 'comment',
        loadChildren: () => import('./comment/comment.module').then(m => m.QuestionnaireCommentModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class QuestionnaireEntityModule {}
