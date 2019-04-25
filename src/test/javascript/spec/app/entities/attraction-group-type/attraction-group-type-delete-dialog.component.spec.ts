/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { SmarttoursTestModule } from '../../../test.module';
import { AttractionGroupTypeDeleteDialogComponent } from 'app/entities/attraction-group-type/attraction-group-type-delete-dialog.component';
import { AttractionGroupTypeService } from 'app/entities/attraction-group-type/attraction-group-type.service';

describe('Component Tests', () => {
    describe('AttractionGroupType Management Delete Component', () => {
        let comp: AttractionGroupTypeDeleteDialogComponent;
        let fixture: ComponentFixture<AttractionGroupTypeDeleteDialogComponent>;
        let service: AttractionGroupTypeService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [SmarttoursTestModule],
                declarations: [AttractionGroupTypeDeleteDialogComponent]
            })
                .overrideTemplate(AttractionGroupTypeDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AttractionGroupTypeDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AttractionGroupTypeService);
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
