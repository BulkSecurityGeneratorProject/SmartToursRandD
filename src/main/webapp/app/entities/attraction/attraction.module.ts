import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SmarttoursSharedModule } from 'app/shared';
import {
    AttractionComponent,
    AttractionDetailComponent,
    AttractionUpdateComponent,
    AttractionDeletePopupComponent,
    AttractionDeleteDialogComponent,
    attractionRoute,
    attractionPopupRoute
} from './';

const ENTITY_STATES = [...attractionRoute, ...attractionPopupRoute];

@NgModule({
    imports: [SmarttoursSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        AttractionComponent,
        AttractionDetailComponent,
        AttractionUpdateComponent,
        AttractionDeleteDialogComponent,
        AttractionDeletePopupComponent
    ],
    entryComponents: [AttractionComponent, AttractionUpdateComponent, AttractionDeleteDialogComponent, AttractionDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SmarttoursAttractionModule {}
