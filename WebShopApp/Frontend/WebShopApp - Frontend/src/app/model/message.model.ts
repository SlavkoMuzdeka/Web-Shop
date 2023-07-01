import { User } from "./user.model";

export class Message {
  constructor(
    private _id?: number,
    private _title?: string,
    private _content?: string,
    private _user?: User
  ) {}

  get id() {
    return this._id;
  }

  get title() {
    return this._title;
  }

  get content() {
    return this._content;
  }

  get user() {
    return this._user;
  }
}
