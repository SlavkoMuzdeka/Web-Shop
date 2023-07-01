import { Product } from "./product.model";
import { User } from "./user.model";

export class Comment {
  
  constructor(
    private _text?: string,
    private _user?: User,
    private _creationDate?: Date,
    private _id?: number,
  ) {}

  get id(){
    return this._id;
  }

  get text(){
    return this._text;
  }

  get user(){
    return this._user;
  }

  get creationDate(){
    return this._creationDate;
  }
}
