/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { SmarttoursTestModule } from '../../../test.module';
import { AttractionDeleteDialogComponent } from 'app/entities/attraction/attraction-delete-dialog.component';
import { AttractionService } from 'app/entities/attraction/attraction.service';

describe('Component Tests', () => {
    describe('Attraction Management Delete Component', () => {
        let comp: AttractionDeleteDialogComponent;
        let fixture: ComponentFixture<AttractionDeleteDialogComponent>;
        let service: AttractionService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [SmarttoursTestModule],
                declarations: [AttractionDeleteDialogComponent]
            })
                .overrideTemplate(AttractionDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AttractionDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AttractionService);
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
