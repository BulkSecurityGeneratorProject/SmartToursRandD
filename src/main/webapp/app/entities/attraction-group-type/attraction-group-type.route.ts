import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { AttractionGroupType } from 'app/shared/model/attraction-group-type.model';
import { AttractionGroupTypeService } from './attraction-group-type.service';
import { AttractionGroupTypeComponent } from './attraction-group-type.component';
import { AttractionGroupTypeDetailComponent } from './attraction-group-type-detail.component';
import { AttractionGroupTypeUpdateComponent } from './attraction-group-type-update.component';
import { AttractionGroupTypeDeletePopupComponent } from './attraction-group-type-delete-dialog.component';
import { IAttractionGroupType } from 'app/shared/model/attraction-group-type.model';

@Injectable({ providedIn: 'root' })
export class AttractionGroupTypeResolve implements Resolve<IAttractionGroupType> {
    constructor(private service: AttractionGroupTypeService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((attractionGroupType: HttpResponse<AttractionGroupType>) => attractionGroupType.body));
        }
        return of(new AttractionGroupType());
    }
}

export const attractionGroupTypeRoute: Routes = [
    {
        path: 'attraction-group-type',
        component: AttractionGroupTypeComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AttractionGroupTypes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'attraction-group-type/:id/view',
        component: AttractionGroupTypeDetailComponent,
        resolve: {
            attractionGroupType: AttractionGroupTypeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AttractionGroupTypes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'attraction-group-type/new',
        component: AttractionGroupTypeUpdateComponent,
        resolve: {
            attractionGroupType: AttractionGroupTypeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AttractionGroupTypes'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'attraction-group-type/:id/edit',
        component: AttractionGroupTypeUpdateComponent,
        resolve: {
            attractionGroupType: AttractionGroupTypeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AttractionGroupTypes'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const attractionGroupTypePopupRoute: Routes = [
    {
        path: 'attraction-group-type/:id/delete',
        component: AttractionGroupTypeDeletePopupComponent,
        resolve: {
            attractionGroupType: AttractionGroupTypeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AttractionGroupTypes'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
