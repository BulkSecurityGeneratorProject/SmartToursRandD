import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SmarttoursSharedModule } from 'app/shared';
import {
    AttractionPurchaseComponent,
    AttractionPurchaseDetailComponent,
    AttractionPurchaseUpdateComponent,
    AttractionPurchaseDeletePopupComponent,
    AttractionPurchaseDeleteDialogComponent,
    attractionPurchaseRoute,
    attractionPurchasePopupRoute
} from './';

const ENTITY_STATES = [...attractionPurchaseRoute, ...attractionPurchasePopupRoute];

@NgModule({
    imports: [SmarttoursSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        AttractionPurchaseComponent,
        AttractionPurchaseDetailComponent,
        AttractionPurchaseUpdateComponent,
        AttractionPurchaseDeleteDialogComponent,
        AttractionPurchaseDeletePopupComponent
    ],
    entryComponents: [
        AttractionPurchaseComponent,
        AttractionPurchaseUpdateComponent,
        AttractionPurchaseDeleteDialogComponent,
        AttractionPurchaseDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SmarttoursAttractionPurchaseModule {}
