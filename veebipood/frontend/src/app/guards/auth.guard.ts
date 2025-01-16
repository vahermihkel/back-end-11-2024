import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { inject, Inject } from '@angular/core';
import { catchError, map, of, switchMap } from 'rxjs';

export const authGuard: CanActivateFn = (route, state) => {
  const authService = inject(AuthService);
  const router = inject(Router);  // ümbersuunamiseks kui on false
  return authService.determineIfLoggedIn().pipe(
    map(isAdmin => {
      if (isAdmin) {
        return true;
      } else {
        router.navigateByUrl('/login');
        return false;
      }
    }),
    catchError(() => {
      router.navigateByUrl('/login');
      return of(false);
    })
  );
};