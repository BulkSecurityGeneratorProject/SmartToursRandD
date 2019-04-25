/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SmarttoursTestModule } from '../../../test.module';
import { AttractionGroupTypeDetailComponent } from 'app/entities/attraction-group-type/attraction-group-type-detail.component';
import { AttractionGroupType } from 'app/shared/model/attraction-group-type.model';

describe('Component Tests', () => {
    describe('AttractionGroupType Management Detail Component', () => {
        let comp: AttractionGroupTypeDetailComponent;
        let fixture: ComponentFixture<AttractionGroupTypeDetailComponent>;
        const route = ({ data: of({ attractionGroupType: new AttractionGroupType(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [SmarttoursTestModule],
                declarations: [AttractionGroupTypeDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(AttractionGroupTypeDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AttractionGroupTypeDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.attractionGroupType).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
