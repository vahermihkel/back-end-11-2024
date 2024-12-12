import { Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { CartComponent } from './cart/cart.component';
import { SignupComponent } from './signup/signup.component';
import { ManageCategoryComponent } from './admin/manage-category/manage-category.component';
import { ManageCharacteristicsComponent } from './admin/manage-characteristics/manage-characteristics.component';
import { ManageProductsComponent } from './admin/manage-products/manage-products.component';
import { AddProductComponent } from './admin/add-product/add-product.component';
import { SupplierComponent } from './admin/supplier/supplier.component';

// path: "cart"   --> see on, mis järgneb localhost:4200-le ehk
//      baasURL-le    err.ee     err.ee/cart

// component: ---> see on, mida näidatakse kui URL on nähtav
//          tema HTMLi + kujunduseks CSS + dünaamikaks .ts

export const routes: Routes = [
  {path: "", component: HomeComponent},
  {path: "cart", component: CartComponent},
  {path: "signup", component: SignupComponent},
  {path: "manage-category", component: ManageCategoryComponent},
  {path: "manage-characteristics", component: ManageCharacteristicsComponent},
  {path: "manage-products", component: ManageProductsComponent},
  {path: "add-product", component: AddProductComponent},
  {path: "supplier", component: SupplierComponent},
];
