import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { AttractionPurchase } from 'app/shared/model/attraction-purchase.model';
import { AttractionPurchaseService } from './attraction-purchase.service';
import { AttractionPurchaseComponent } from './attraction-purchase.component';
import { AttractionPurchaseDetailComponent } from './attraction-purchase-detail.component';
import { AttractionPurchaseUpdateComponent } from './attraction-purchase-update.component';
import { AttractionPurchaseDeletePopupComponent } from './attraction-purchase-delete-dialog.component';
import { IAttractionPurchase } from 'app/shared/model/attraction-purchase.model';

@Injectable({ providedIn: 'root' })
export class AttractionPurchaseResolve implements Resolve<IAttractionPurchase> {
    constructor(private service: AttractionPurchaseService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((attractionPurchase: HttpResponse<AttractionPurchase>) => attractionPurchase.body));
        }
        return of(new AttractionPurchase());
    }
}

export const attractionPurchaseRoute: Routes = [
    {
        path: 'attraction-purchase',
        component: AttractionPurchaseComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AttractionPurchases'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'attraction-purchase/:id/view',
        component: AttractionPurchaseDetailComponent,
        resolve: {
            attractionPurchase: AttractionPurchaseResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AttractionPurchases'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'attraction-purchase/new',
        component: AttractionPurchaseUpdateComponent,
        resolve: {
            attractionPurchase: AttractionPurchaseResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AttractionPurchases'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'attraction-purchase/:id/edit',
        component: AttractionPurchaseUpdateComponent,
        resolve: {
            attractionPurchase: AttractionPurchaseResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AttractionPurchases'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const attractionPurchasePopupRoute: Routes = [
    {
        path: 'attraction-purchase/:id/delete',
        component: AttractionPurchaseDeletePopupComponent,
        resolve: {
            attractionPurchase: AttractionPurchaseResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AttractionPurchases'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
