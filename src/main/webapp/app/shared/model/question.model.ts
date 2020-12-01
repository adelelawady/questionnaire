import { IAnswer } from 'app/shared/model/answer.model';
import { ISubject } from 'app/shared/model/subject.model';
import { IComment } from 'app/shared/model/comment.model';

export interface IQuestion {
  id?: string;
  title?: string;
  content?: string;
  views?: number;
  likes?: number;
  answerd?: boolean;
  bestAnswerId?: string;
  answers?: IAnswer[];
  subjects?: ISubject[];
  ownerId?: string;
  comments?: IComment[];
}

export class Question implements IQuestion {
  constructor(
    public id?: string,
    public title?: string,
    public content?: string,
    public views?: number,
    public likes?: number,
    public answerd?: boolean,
    public bestAnswerId?: string,
    public answers?: IAnswer[],
    public subjects?: ISubject[],
    public ownerId?: string,
    public comments?: IComment[]
  ) {
    this.answerd = this.answerd || false;
  }
}
