import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanDeactivate, CanDeactivateFn, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';

export interface CanComponentDeactivate {
  canDeactivate: () => Observable<boolean> | Promise<boolean> | boolean
}




@Injectable({
  providedIn: 'root'
 })
// export class UnsavedGuard implements CanDeactivate<DeliverystatusComponent> {
//   canDeactivate(component:DeliverystatusComponent){
//     if(component.isDirty){
//       window.confirm("you have some unsaved chnages?are you sure want to move?");
//     }
//     return true;
//   }
// }

export class deactivateGuard implements CanDeactivate<CanComponentDeactivate> {

  constructor(private router: Router) {}

  canDeactivate(
    component: CanComponentDeactivate,
    currentRoute: ActivatedRouteSnapshot,
    currentState: RouterStateSnapshot,
    nextState?: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
      let url: string = currentState.url;
      console.log('Url: '+ url);
 
      console.log(component.canDeactivate())
      return component.canDeactivate ? component.canDeactivate() : true;
  }
}