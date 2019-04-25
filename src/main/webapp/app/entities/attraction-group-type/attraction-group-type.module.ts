import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SmarttoursSharedModule } from 'app/shared';
import {
    AttractionGroupTypeComponent,
    AttractionGroupTypeDetailComponent,
    AttractionGroupTypeUpdateComponent,
    AttractionGroupTypeDeletePopupComponent,
    AttractionGroupTypeDeleteDialogComponent,
    attractionGroupTypeRoute,
    attractionGroupTypePopupRoute
} from './';

const ENTITY_STATES = [...attractionGroupTypeRoute, ...attractionGroupTypePopupRoute];

@NgModule({
    imports: [SmarttoursSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        AttractionGroupTypeComponent,
        AttractionGroupTypeDetailComponent,
        AttractionGroupTypeUpdateComponent,
        AttractionGroupTypeDeleteDialogComponent,
        AttractionGroupTypeDeletePopupComponent
    ],
    entryComponents: [
        AttractionGroupTypeComponent,
        AttractionGroupTypeUpdateComponent,
        AttractionGroupTypeDeleteDialogComponent,
        AttractionGroupTypeDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SmarttoursAttractionGroupTypeModule {}
