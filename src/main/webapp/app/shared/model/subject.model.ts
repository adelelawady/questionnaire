import { IQuestion } from 'app/shared/model/question.model';

export interface ISubject {
  id?: string;
  name?: string;
  creatorId?: string;
  questions?: IQuestion[];
}

export class Subject implements ISubject {
  constructor(public id?: string, public name?: string, public creatorId?: string, public questions?: IQuestion[]) {}
}
