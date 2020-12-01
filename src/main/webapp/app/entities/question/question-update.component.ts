import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IQuestion, Question } from 'app/shared/model/question.model';
import { QuestionService } from './question.service';
import { IAnswer } from 'app/shared/model/answer.model';
import { AnswerService } from 'app/entities/answer/answer.service';
import { ISubject } from 'app/shared/model/subject.model';
import { SubjectService } from 'app/entities/subject/subject.service';
import { IApplicationUser } from 'app/shared/model/application-user.model';
import { ApplicationUserService } from 'app/entities/application-user/application-user.service';

type SelectableEntity = IAnswer | ISubject | IApplicationUser;

type SelectableManyToManyEntity = IAnswer | ISubject;

@Component({
  selector: 'jhi-question-update',
  templateUrl: './question-update.component.html',
})
export class QuestionUpdateComponent implements OnInit {
  isSaving = false;
  answers: IAnswer[] = [];
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
    answers: [],
    subjects: [],
    ownerId: [],
  });

  constructor(
    protected questionService: QuestionService,
    protected answerService: AnswerService,
    protected subjectService: SubjectService,
    protected applicationUserService: ApplicationUserService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ question }) => {
      this.updateForm(question);

      this.answerService.query().subscribe((res: HttpResponse<IAnswer[]>) => (this.answers = res.body || []));

      this.subjectService.query().subscribe((res: HttpResponse<ISubject[]>) => (this.subjects = res.body || []));

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
      answers: question.answers,
      subjects: question.subjects,
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
      answers: this.editForm.get(['answers'])!.value,
      subjects: this.editForm.get(['subjects'])!.value,
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

  getSelected(selectedVals: SelectableManyToManyEntity[], option: SelectableManyToManyEntity): SelectableManyToManyEntity {
    if (selectedVals) {
      for (let i = 0; i < selectedVals.length; i++) {
        if (option.id === selectedVals[i].id) {
          return selectedVals[i];
        }
      }
    }
    return option;
  }
}
