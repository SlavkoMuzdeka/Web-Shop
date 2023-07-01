export class User {
  constructor(
    private _firstName?: string,
    private _lastName?: string,
    private _id?: number,
    private _city?: string,
    private _userName?: string,
    private _password?: string,
    private _mail?: string,
    private _avatar?: Uint8Array
  ) {}

  get id() {
    return this._id;
  }

  get firstName() {
    return this._firstName;
  }

  get lastName() {
    return this._lastName;
  }

  get city() {
    return this._city;
  }

  get userName() {
    return this._userName;
  }

  get password() {
   return this._password;
  }

  get mail() {
    return this._mail;
  }

  get avatar() {
    return this._avatar;
  }

  set avatar(avatar:Uint8Array | undefined){
    this._avatar = avatar;
  }
  
}
