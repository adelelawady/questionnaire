import { IAnswer } from 'app/shared/model/answer.model';
import { IComment } from 'app/shared/model/comment.model';

export interface IQuestion {
  id?: string;
  title?: string;
  content?: string;
  views?: number;
  likes?: number;
  answerd?: boolean;
  bestAnswerId?: string;
  subjectId?: string;
  answers?: IAnswer[];
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
    public subjectId?: string,
    public answers?: IAnswer[],
    public ownerId?: string,
    public comments?: IComment[]
  ) {
    this.answerd = this.answerd || false;
  }
}
