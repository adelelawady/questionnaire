export interface ISubject {
  id?: string;
  name?: string;
  creatorId?: string;
}

export class Subject implements ISubject {
  constructor(public id?: string, public name?: string, public creatorId?: string) {}
}
