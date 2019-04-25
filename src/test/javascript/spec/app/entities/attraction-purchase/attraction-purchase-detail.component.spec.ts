/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SmarttoursTestModule } from '../../../test.module';
import { AttractionPurchaseDetailComponent } from 'app/entities/attraction-purchase/attraction-purchase-detail.component';
import { AttractionPurchase } from 'app/shared/model/attraction-purchase.model';

describe('Component Tests', () => {
    describe('AttractionPurchase Management Detail Component', () => {
        let comp: AttractionPurchaseDetailComponent;
        let fixture: ComponentFixture<AttractionPurchaseDetailComponent>;
        const route = ({ data: of({ attractionPurchase: new AttractionPurchase(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [SmarttoursTestModule],
                declarations: [AttractionPurchaseDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(AttractionPurchaseDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AttractionPurchaseDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.attractionPurchase).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
