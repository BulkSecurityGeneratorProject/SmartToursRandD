/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { SmarttoursTestModule } from '../../../test.module';
import { AttractionPurchaseUpdateComponent } from 'app/entities/attraction-purchase/attraction-purchase-update.component';
import { AttractionPurchaseService } from 'app/entities/attraction-purchase/attraction-purchase.service';
import { AttractionPurchase } from 'app/shared/model/attraction-purchase.model';

describe('Component Tests', () => {
    describe('AttractionPurchase Management Update Component', () => {
        let comp: AttractionPurchaseUpdateComponent;
        let fixture: ComponentFixture<AttractionPurchaseUpdateComponent>;
        let service: AttractionPurchaseService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [SmarttoursTestModule],
                declarations: [AttractionPurchaseUpdateComponent]
            })
                .overrideTemplate(AttractionPurchaseUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(AttractionPurchaseUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AttractionPurchaseService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new AttractionPurchase(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.attractionPurchase = entity;
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
                    const entity = new AttractionPurchase();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.attractionPurchase = entity;
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
