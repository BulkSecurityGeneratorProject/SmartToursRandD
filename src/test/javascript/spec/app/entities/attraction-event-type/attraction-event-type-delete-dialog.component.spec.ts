/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { SmarttoursTestModule } from '../../../test.module';
import { AttractionEventTypeDeleteDialogComponent } from 'app/entities/attraction-event-type/attraction-event-type-delete-dialog.component';
import { AttractionEventTypeService } from 'app/entities/attraction-event-type/attraction-event-type.service';

describe('Component Tests', () => {
    describe('AttractionEventType Management Delete Component', () => {
        let comp: AttractionEventTypeDeleteDialogComponent;
        let fixture: ComponentFixture<AttractionEventTypeDeleteDialogComponent>;
        let service: AttractionEventTypeService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [SmarttoursTestModule],
                declarations: [AttractionEventTypeDeleteDialogComponent]
            })
                .overrideTemplate(AttractionEventTypeDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AttractionEventTypeDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AttractionEventTypeService);
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
