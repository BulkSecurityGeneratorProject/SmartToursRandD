import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Attraction } from 'app/shared/model/attraction.model';
import { AttractionService } from './attraction.service';
import { AttractionComponent } from './attraction.component';
import { AttractionDetailComponent } from './attraction-detail.component';
import { AttractionUpdateComponent } from './attraction-update.component';
import { AttractionDeletePopupComponent } from './attraction-delete-dialog.component';
import { IAttraction } from 'app/shared/model/attraction.model';

@Injectable({ providedIn: 'root' })
export class AttractionResolve implements Resolve<IAttraction> {
    constructor(private service: AttractionService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((attraction: HttpResponse<Attraction>) => attraction.body));
        }
        return of(new Attraction());
    }
}

export const attractionRoute: Routes = [
    {
        path: 'attraction',
        component: AttractionComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Attractions'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'attraction/:id/view',
        component: AttractionDetailComponent,
        resolve: {
            attraction: AttractionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Attractions'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'attraction/new',
        component: AttractionUpdateComponent,
        resolve: {
            attraction: AttractionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Attractions'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'attraction/:id/edit',
        component: AttractionUpdateComponent,
        resolve: {
            attraction: AttractionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Attractions'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const attractionPopupRoute: Routes = [
    {
        path: 'attraction/:id/delete',
        component: AttractionDeletePopupComponent,
        resolve: {
            attraction: AttractionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Attractions'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
