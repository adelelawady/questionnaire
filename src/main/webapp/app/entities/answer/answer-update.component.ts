import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IAnswer, Answer } from 'app/shared/model/answer.model';
import { AnswerService } from './answer.service';
import { IApplicationUser } from 'app/shared/model/application-user.model';
import { ApplicationUserService } from 'app/entities/application-user/application-user.service';

@Component({
  selector: 'jhi-answer-update',
  templateUrl: './answer-update.component.html',
})
export class AnswerUpdateComponent implements OnInit {
  isSaving = false;
  applicationusers: IApplicationUser[] = [];

  editForm = this.fb.group({
    id: [],
    content: [],
    votesUp: [],
    votesDown: [],
    ownerId: [],
  });

  constructor(
    protected answerService: AnswerService,
    protected applicationUserService: ApplicationUserService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ answer }) => {
      this.updateForm(answer);

      this.applicationUserService.query().subscribe((res: HttpResponse<IApplicationUser[]>) => (this.applicationusers = res.body || []));
    });
  }

  updateForm(answer: IAnswer): void {
    this.editForm.patchValue({
      id: answer.id,
      content: answer.content,
      votesUp: answer.votesUp,
      votesDown: answer.votesDown,
      ownerId: answer.ownerId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const answer = this.createFromForm();
    if (answer.id !== undefined) {
      this.subscribeToSaveResponse(this.answerService.update(answer));
    } else {
      this.subscribeToSaveResponse(this.answerService.create(answer));
    }
  }

  private createFromForm(): IAnswer {
    return {
      ...new Answer(),
      id: this.editForm.get(['id'])!.value,
      content: this.editForm.get(['content'])!.value,
      votesUp: this.editForm.get(['votesUp'])!.value,
      votesDown: this.editForm.get(['votesDown'])!.value,
      ownerId: this.editForm.get(['ownerId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAnswer>>): void {
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

  trackById(index: number, item: IApplicationUser): any {
    return item.id;
  }
}
