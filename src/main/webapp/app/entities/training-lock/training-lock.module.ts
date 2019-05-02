import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SmarttoursSharedModule } from 'app/shared';
import {
    TrainingLockComponent,
    TrainingLockDetailComponent,
    TrainingLockUpdateComponent,
    TrainingLockDeletePopupComponent,
    TrainingLockDeleteDialogComponent,
    trainingLockRoute,
    trainingLockPopupRoute
} from './';

const ENTITY_STATES = [...trainingLockRoute, ...trainingLockPopupRoute];

@NgModule({
    imports: [SmarttoursSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        TrainingLockComponent,
        TrainingLockDetailComponent,
        TrainingLockUpdateComponent,
        TrainingLockDeleteDialogComponent,
        TrainingLockDeletePopupComponent
    ],
    entryComponents: [
        TrainingLockComponent,
        TrainingLockUpdateComponent,
        TrainingLockDeleteDialogComponent,
        TrainingLockDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SmarttoursTrainingLockModule {}
