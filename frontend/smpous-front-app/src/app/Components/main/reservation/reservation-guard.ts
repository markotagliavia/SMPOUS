import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot} from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from "../../../Services/auth.service";


@Injectable()//This must be here, if we want to inject authService!
export class ReservationGuard implements CanActivate{

    // prosledjen autenitfikacioni servis
    constructor(private authService: AuthService){
    }

    /// metoda vraca boolean ili Observable ili Promise
    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | Observable<boolean> | Promise<boolean> {
        return  this.authService.isLoggedInRole("Manager") || this.authService.isLoggedInRole("Admin") || this.authService.isLoggedInRole("AppUser"); // vrsi proveru da li je logovan i vraca true (sme da ode na protecte) ili false (ne sme da ode na protected)
    }


}
