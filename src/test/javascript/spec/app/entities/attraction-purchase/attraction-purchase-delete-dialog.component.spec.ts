/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { SmarttoursTestModule } from '../../../test.module';
import { AttractionPurchaseDeleteDialogComponent } from 'app/entities/attraction-purchase/attraction-purchase-delete-dialog.component';
import { AttractionPurchaseService } from 'app/entities/attraction-purchase/attraction-purchase.service';

describe('Component Tests', () => {
    describe('AttractionPurchase Management Delete Component', () => {
        let comp: AttractionPurchaseDeleteDialogComponent;
        let fixture: ComponentFixture<AttractionPurchaseDeleteDialogComponent>;
        let service: AttractionPurchaseService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [SmarttoursTestModule],
                declarations: [AttractionPurchaseDeleteDialogComponent]
            })
                .overrideTemplate(AttractionPurchaseDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AttractionPurchaseDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AttractionPurchaseService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
