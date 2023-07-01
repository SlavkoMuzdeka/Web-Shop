export class Category {
  constructor(
    private _id?: number,
    private _name?: string,
    private _categories?: Category[]
  ) {}

  get id(){
    return this._id;
  }

  get name(){
    return this._name;
  }

  get categories(){
    return this._categories;
  }
}
