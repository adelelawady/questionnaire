import { IAnswer } from 'app/shared/model/answer.model';
import { IQuestion } from 'app/shared/model/question.model';
import { CommentType } from 'app/shared/model/enumerations/comment-type.model';

export interface IComment {
  id?: string;
  name?: string;
  commentType?: CommentType;
  refQuestionId?: string;
  answers?: IAnswer[];
  questions?: IQuestion[];
  ownerId?: string;
}

export class Comment implements IComment {
  constructor(
    public id?: string,
    public name?: string,
    public commentType?: CommentType,
    public refQuestionId?: string,
    public answers?: IAnswer[],
    public questions?: IQuestion[],
    public ownerId?: string
  ) {}
}
