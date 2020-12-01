import { IComment } from 'app/shared/model/comment.model';
import { ISubject } from 'app/shared/model/subject.model';
import { IQuestion } from 'app/shared/model/question.model';
import { IAnswer } from 'app/shared/model/answer.model';

export interface IApplicationUser {
  id?: string;
  internalUserId?: string;
  comments?: IComment[];
  subjects?: ISubject[];
  questions?: IQuestion[];
  answers?: IAnswer[];
}

export class ApplicationUser implements IApplicationUser {
  constructor(
    public id?: string,
    public internalUserId?: string,
    public comments?: IComment[],
    public subjects?: ISubject[],
    public questions?: IQuestion[],
    public answers?: IAnswer[]
  ) {}
}
