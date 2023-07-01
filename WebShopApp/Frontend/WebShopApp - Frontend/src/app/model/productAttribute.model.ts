import { Attribute } from "./attribute.model";

export class ProductAttribute {
  constructor(
    private _attribute?: string,
    private _attributeEnt?: Attribute
  ) {}

  get attribute(){
    return this._attribute;
  }

  get attributeEnt(){
    return this._attributeEnt;
  }
}
