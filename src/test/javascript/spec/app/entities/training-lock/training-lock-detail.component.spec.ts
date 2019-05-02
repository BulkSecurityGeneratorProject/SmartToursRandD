/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SmarttoursTestModule } from '../../../test.module';
import { TrainingLockDetailComponent } from 'app/entities/training-lock/training-lock-detail.component';
import { TrainingLock } from 'app/shared/model/training-lock.model';

describe('Component Tests', () => {
    describe('TrainingLock Management Detail Component', () => {
        let comp: TrainingLockDetailComponent;
        let fixture: ComponentFixture<TrainingLockDetailComponent>;
        const route = ({ data: of({ trainingLock: new TrainingLock(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [SmarttoursTestModule],
                declarations: [TrainingLockDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(TrainingLockDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TrainingLockDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.trainingLock).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
