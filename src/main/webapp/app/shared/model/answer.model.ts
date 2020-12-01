import { IComment } from 'app/shared/model/comment.model';

export interface IAnswer {
  id?: string;
  content?: string;
  votesUp?: number;
  votesDown?: number;
  ownerId?: string;
  questionId?: string;
  comments?: IComment[];
}

export class Answer implements IAnswer {
  constructor(
    public id?: string,
    public content?: string,
    public votesUp?: number,
    public votesDown?: number,
    public ownerId?: string,
    public questionId?: string,
    public comments?: IComment[]
  ) {}
}
