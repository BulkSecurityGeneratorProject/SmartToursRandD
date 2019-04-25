/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SmarttoursTestModule } from '../../../test.module';
import { AttractionDetailComponent } from 'app/entities/attraction/attraction-detail.component';
import { Attraction } from 'app/shared/model/attraction.model';

describe('Component Tests', () => {
    describe('Attraction Management Detail Component', () => {
        let comp: AttractionDetailComponent;
        let fixture: ComponentFixture<AttractionDetailComponent>;
        const route = ({ data: of({ attraction: new Attraction(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [SmarttoursTestModule],
                declarations: [AttractionDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(AttractionDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AttractionDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.attraction).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
