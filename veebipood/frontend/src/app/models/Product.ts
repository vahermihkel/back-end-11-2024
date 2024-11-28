import { Category } from "./Category";
import { Characteristic } from "./Characteristic";

export class Product {
  constructor(
    public id: number,
    public name: string,
    public price: number,
    public active: boolean,
    public image: string,
    public nutrients: Nutrients,
    public category: Category,
    public characteristics: Characteristic[]
  ) {}
}

export class Nutrients {
  constructor(
    public id: number,
    public protein: number,
    public carbohydrate: number,
    public fat: number
  ) {}
}