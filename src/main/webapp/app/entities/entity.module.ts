import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { SmarttoursAttractionModule } from './attraction/attraction.module';
import { SmarttoursAttractionGroupTypeModule } from './attraction-group-type/attraction-group-type.module';
import { SmarttoursAttractionEventTypeModule } from './attraction-event-type/attraction-event-type.module';
import { SmarttoursAttractionPurchaseModule } from './attraction-purchase/attraction-purchase.module';
import { SmarttoursTrainingLockModule } from './training-lock/training-lock.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        SmarttoursAttractionModule,
        SmarttoursAttractionGroupTypeModule,
        SmarttoursAttractionEventTypeModule,
        SmarttoursAttractionPurchaseModule,
        SmarttoursTrainingLockModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SmarttoursEntityModule {}
