import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IQuestion, Question } from 'app/shared/model/question.model';
import { QuestionService } from './question.service';
import { ISubject } from 'app/shared/model/subject.model';
import { SubjectService } from 'app/entities/subject/subject.service';
import { IApplicationUser } from 'app/shared/model/application-user.model';
import { ApplicationUserService } from 'app/entities/application-user/application-user.service';

type SelectableEntity = ISubject | IApplicationUser;

@Component({
  selector: 'jhi-question-update',
  templateUrl: './question-update.component.html',
})
export class QuestionUpdateComponent implements OnInit {
  isSaving = false;
  subjects: ISubject[] = [];
  applicationusers: IApplicationUser[] = [];

  editForm = this.fb.group({
    id: [],
    title: [],
    content: [],
    views: [],
    likes: [],
    answerd: [],
    bestAnswerId: [],
    subjectId: [],
    ownerId: [],
  });

  constructor(
    protected questionService: QuestionService,
    protected subjectService: SubjectService,
    protected applicationUserService: ApplicationUserService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ question }) => {
      this.updateForm(question);

      this.subjectService
        .query({ filter: 'question-is-null' })
        .pipe(
          map((res: HttpResponse<ISubject[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: ISubject[]) => {
          if (!question.subjectId) {
            this.subjects = resBody;
          } else {
            this.subjectService
              .find(question.subjectId)
              .pipe(
                map((subRes: HttpResponse<ISubject>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: ISubject[]) => (this.subjects = concatRes));
          }
        });

      this.applicationUserService.query().subscribe((res: HttpResponse<IApplicationUser[]>) => (this.applicationusers = res.body || []));
    });
  }

  updateForm(question: IQuestion): void {
    this.editForm.patchValue({
      id: question.id,
      title: question.title,
      content: question.content,
      views: question.views,
      likes: question.likes,
      answerd: question.answerd,
      bestAnswerId: question.bestAnswerId,
      subjectId: question.subjectId,
      ownerId: question.ownerId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const question = this.createFromForm();
    if (question.id !== undefined) {
      this.subscribeToSaveResponse(this.questionService.update(question));
    } else {
      this.subscribeToSaveResponse(this.questionService.create(question));
    }
  }

  private createFromForm(): IQuestion {
    return {
      ...new Question(),
      id: this.editForm.get(['id'])!.value,
      title: this.editForm.get(['title'])!.value,
      content: this.editForm.get(['content'])!.value,
      views: this.editForm.get(['views'])!.value,
      likes: this.editForm.get(['likes'])!.value,
      answerd: this.editForm.get(['answerd'])!.value,
      bestAnswerId: this.editForm.get(['bestAnswerId'])!.value,
      subjectId: this.editForm.get(['subjectId'])!.value,
      ownerId: this.editForm.get(['ownerId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IQuestion>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
