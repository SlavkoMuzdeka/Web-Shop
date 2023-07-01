import { Category } from "./category.model";
import { Comment } from "./comment.model";
import { Image } from "./image.model";
import { ProductAttribute } from "./productAttribute.model";
import { User } from "./user.model";

export class Product {
  constructor(
    private _id: number,
    private _title: string,
    private _price: number,
    private _description?: string,
    private _type?: string,
    private _location?: string,
    private _contact?: string,
    private _creationDate?: Date,
    private _isActive?: boolean,
    private _isSold?: boolean,
    private _isFinished?: boolean,
    private _comments?: Comment[],
    private _image?: Image,
    private _images?: Image[],
    private _productAttributes?: ProductAttribute[],
    private _category?: Category,
    private _seller?: User,
  ) {}

  get id(){
    return this._id;
  }

  get title(){
    return this._title;
  }

  get description(){
    return this._description;
  }

  get price(){
    return this._price;
  }

  get type(){
    return this._type;
  }

  get location(){
    return this._location;
  }

  get contact(){
    return this._contact;
  }

  get creationDate(){
    return this._creationDate;
  }

  get isActive(){
    return this._isActive;
  }

  get isSold(){
    return this._isSold;
  }

  get isFinished() {
    return this._isFinished;
  }

  get comments(){
    return this._comments;
  }

  get image(){
    return this._image;
  }

  get images(){
    return this._images;
  }

  get productAttributes(){
    return this._productAttributes;
  }

  get category(){
    return this._category;
  }

  get seller(){
    return this._seller;
  }

  set isSold(value: boolean | undefined) {
    this._isSold = value;
  }

  set isFinished(value: boolean | undefined) {
    this._isFinished = value;
  }

}
