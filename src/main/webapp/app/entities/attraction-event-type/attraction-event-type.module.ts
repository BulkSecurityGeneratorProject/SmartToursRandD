import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SmarttoursSharedModule } from 'app/shared';
import {
    AttractionEventTypeComponent,
    AttractionEventTypeDetailComponent,
    AttractionEventTypeUpdateComponent,
    AttractionEventTypeDeletePopupComponent,
    AttractionEventTypeDeleteDialogComponent,
    attractionEventTypeRoute,
    attractionEventTypePopupRoute
} from './';

const ENTITY_STATES = [...attractionEventTypeRoute, ...attractionEventTypePopupRoute];

@NgModule({
    imports: [SmarttoursSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        AttractionEventTypeComponent,
        AttractionEventTypeDetailComponent,
        AttractionEventTypeUpdateComponent,
        AttractionEventTypeDeleteDialogComponent,
        AttractionEventTypeDeletePopupComponent
    ],
    entryComponents: [
        AttractionEventTypeComponent,
        AttractionEventTypeUpdateComponent,
        AttractionEventTypeDeleteDialogComponent,
        AttractionEventTypeDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SmarttoursAttractionEventTypeModule {}
