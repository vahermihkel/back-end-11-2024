import { Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { CartComponent } from './cart/cart.component';
import { SignupComponent } from './signup/signup.component';
import { ManageCategoryComponent } from './admin/manage-category/manage-category.component';
import { ManageCharacteristicsComponent } from './admin/manage-characteristics/manage-characteristics.component';
import { ManageProductsComponent } from './admin/manage-products/manage-products.component';
import { AddProductComponent } from './admin/add-product/add-product.component';
import { SupplierComponent } from './admin/supplier/supplier.component';
import { LoginComponent } from './login/login.component';
import { AdminComponent } from './admin/admin/admin.component';
import { authGuard } from './guards/auth.guard';

// path: "cart"   --> see on, mis järgneb localhost:4200-le ehk
//      baasURL-le    err.ee     err.ee/cart

// component: ---> see on, mida näidatakse kui URL on nähtav
//          tema HTMLi + kujunduseks CSS + dünaamikaks .ts

export const routes: Routes = [
  {path: "", component: HomeComponent},
  {path: "cart", component: CartComponent},
  {path: "signup", component: SignupComponent},
  {path: "manage-category", component: ManageCategoryComponent, canActivate: [authGuard]},
  {path: "manage-characteristics", component: ManageCharacteristicsComponent, canActivate: [authGuard]},
  {path: "manage-products", component: ManageProductsComponent, canActivate: [authGuard]},
  {path: "add-product", component: AddProductComponent, canActivate: [authGuard]},
  {path: "supplier", component: SupplierComponent, canActivate: [authGuard]},
  {path: "login", component: LoginComponent},
  {path: "admin", component: AdminComponent, canActivate: [authGuard]},
];
