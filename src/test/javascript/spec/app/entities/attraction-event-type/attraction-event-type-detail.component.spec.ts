/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SmarttoursTestModule } from '../../../test.module';
import { AttractionEventTypeDetailComponent } from 'app/entities/attraction-event-type/attraction-event-type-detail.component';
import { AttractionEventType } from 'app/shared/model/attraction-event-type.model';

describe('Component Tests', () => {
    describe('AttractionEventType Management Detail Component', () => {
        let comp: AttractionEventTypeDetailComponent;
        let fixture: ComponentFixture<AttractionEventTypeDetailComponent>;
        const route = ({ data: of({ attractionEventType: new AttractionEventType(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [SmarttoursTestModule],
                declarations: [AttractionEventTypeDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(AttractionEventTypeDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AttractionEventTypeDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.attractionEventType).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
