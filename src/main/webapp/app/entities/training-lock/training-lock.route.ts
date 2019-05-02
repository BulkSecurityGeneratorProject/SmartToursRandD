import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { TrainingLock } from 'app/shared/model/training-lock.model';
import { TrainingLockService } from './training-lock.service';
import { TrainingLockComponent } from './training-lock.component';
import { TrainingLockDetailComponent } from './training-lock-detail.component';
import { TrainingLockUpdateComponent } from './training-lock-update.component';
import { TrainingLockDeletePopupComponent } from './training-lock-delete-dialog.component';
import { ITrainingLock } from 'app/shared/model/training-lock.model';

@Injectable({ providedIn: 'root' })
export class TrainingLockResolve implements Resolve<ITrainingLock> {
    constructor(private service: TrainingLockService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((trainingLock: HttpResponse<TrainingLock>) => trainingLock.body));
        }
        return of(new TrainingLock());
    }
}

export const trainingLockRoute: Routes = [
    {
        path: 'training-lock',
        component: TrainingLockComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TrainingLocks'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'training-lock/:id/view',
        component: TrainingLockDetailComponent,
        resolve: {
            trainingLock: TrainingLockResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TrainingLocks'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'training-lock/new',
        component: TrainingLockUpdateComponent,
        resolve: {
            trainingLock: TrainingLockResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TrainingLocks'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'training-lock/:id/edit',
        component: TrainingLockUpdateComponent,
        resolve: {
            trainingLock: TrainingLockResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TrainingLocks'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const trainingLockPopupRoute: Routes = [
    {
        path: 'training-lock/:id/delete',
        component: TrainingLockDeletePopupComponent,
        resolve: {
            trainingLock: TrainingLockResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TrainingLocks'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
