import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { AttractionEventType } from 'app/shared/model/attraction-event-type.model';
import { AttractionEventTypeService } from './attraction-event-type.service';
import { AttractionEventTypeComponent } from './attraction-event-type.component';
import { AttractionEventTypeDetailComponent } from './attraction-event-type-detail.component';
import { AttractionEventTypeUpdateComponent } from './attraction-event-type-update.component';
import { AttractionEventTypeDeletePopupComponent } from './attraction-event-type-delete-dialog.component';
import { IAttractionEventType } from 'app/shared/model/attraction-event-type.model';

@Injectable({ providedIn: 'root' })
export class AttractionEventTypeResolve implements Resolve<IAttractionEventType> {
    constructor(private service: AttractionEventTypeService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((attractionEventType: HttpResponse<AttractionEventType>) => attractionEventType.body));
        }
        return of(new AttractionEventType());
    }
}

export const attractionEventTypeRoute: Routes = [
    {
        path: 'attraction-event-type',
        component: AttractionEventTypeComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AttractionEventTypes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'attraction-event-type/:id/view',
        component: AttractionEventTypeDetailComponent,
        resolve: {
            attractionEventType: AttractionEventTypeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AttractionEventTypes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'attraction-event-type/new',
        component: AttractionEventTypeUpdateComponent,
        resolve: {
            attractionEventType: AttractionEventTypeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AttractionEventTypes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'attraction-event-type/:id/edit',
        component: AttractionEventTypeUpdateComponent,
        resolve: {
            attractionEventType: AttractionEventTypeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AttractionEventTypes'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const attractionEventTypePopupRoute: Routes = [
    {
        path: 'attraction-event-type/:id/delete',
        component: AttractionEventTypeDeletePopupComponent,
        resolve: {
            attractionEventType: AttractionEventTypeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AttractionEventTypes'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
