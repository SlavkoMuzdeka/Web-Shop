export class Image {
  constructor(private _img: string, private _id?: number) {}

  get img() {
    return this._img;
  }

  get id() {
    return this._id;
  }
}
