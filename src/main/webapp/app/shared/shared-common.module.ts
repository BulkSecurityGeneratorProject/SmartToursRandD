import { NgModule } from '@angular/core';

import { SmarttoursSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent } from './';

@NgModule({
    imports: [SmarttoursSharedLibsModule],
    declarations: [JhiAlertComponent, JhiAlertErrorComponent],
    exports: [SmarttoursSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent]
})
export class SmarttoursSharedCommonModule {}
