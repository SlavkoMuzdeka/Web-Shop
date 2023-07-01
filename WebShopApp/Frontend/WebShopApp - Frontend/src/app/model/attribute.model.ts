import { Category } from './category.model';

export class Attribute {
  constructor(
    private _id?: number,
    private _category?: Category,
    private _name?: string,
    private _type?: any,
    private _userInput?: any
  ) {}

  get id() {
    return this._id;
  }

  get category() {
    return this._category;
  }

  get name() {
    return this._name;
  }

  get type() {
    return this._type;
  }

  get userInput() {
    return this._userInput;
  }

}
