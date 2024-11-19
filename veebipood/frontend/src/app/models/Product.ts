export class Product {
  constructor(
    public id: number,
    public name: string,
    public active: boolean,
    public image: string,
    public nutrients: Nutrients
  ) {}
}

class Nutrients {
  constructor(
    public protein: number,
    public carbohydrate: number,
    public fat: number
  ) {}
}