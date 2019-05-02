/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { SmarttoursTestModule } from '../../../test.module';
import { TrainingLockDeleteDialogComponent } from 'app/entities/training-lock/training-lock-delete-dialog.component';
import { TrainingLockService } from 'app/entities/training-lock/training-lock.service';

describe('Component Tests', () => {
    describe('TrainingLock Management Delete Component', () => {
        let comp: TrainingLockDeleteDialogComponent;
        let fixture: ComponentFixture<TrainingLockDeleteDialogComponent>;
        let service: TrainingLockService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [SmarttoursTestModule],
                declarations: [TrainingLockDeleteDialogComponent]
            })
                .overrideTemplate(TrainingLockDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TrainingLockDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TrainingLockService);
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
