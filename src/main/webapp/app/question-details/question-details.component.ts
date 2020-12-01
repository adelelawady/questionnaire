import { HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { QuestionService } from 'app/entities/question/question.service';
import { IQuestion } from '../shared/model/question.model';

@Component({
  templateUrl: './question-details.component.html',
  styleUrls: ['./question-details.component.scss'],
})
export class QuestionDetailsComponent implements OnInit {
  question: IQuestion | null = null;
  constructor(protected activatedRoute: ActivatedRoute, private questionService: QuestionService) {}

  ngOnInit(): void {
    this.activatedRoute.params.subscribe(({ id }) => {
      this.questionService.find(id).subscribe((res: HttpResponse<IQuestion>) => {
        this.question = res.body;
      });
    });
  }
}
