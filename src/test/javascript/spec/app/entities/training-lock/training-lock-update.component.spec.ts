/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { SmarttoursTestModule } from '../../../test.module';
import { TrainingLockUpdateComponent } from 'app/entities/training-lock/training-lock-update.component';
import { TrainingLockService } from 'app/entities/training-lock/training-lock.service';
import { TrainingLock } from 'app/shared/model/training-lock.model';

describe('Component Tests', () => {
    describe('TrainingLock Management Update Component', () => {
        let comp: TrainingLockUpdateComponent;
        let fixture: ComponentFixture<TrainingLockUpdateComponent>;
        let service: TrainingLockService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [SmarttoursTestModule],
                declarations: [TrainingLockUpdateComponent]
            })
                .overrideTemplate(TrainingLockUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(TrainingLockUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TrainingLockService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new TrainingLock(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.trainingLock = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new TrainingLock();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.trainingLock = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
