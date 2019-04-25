/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { SmarttoursTestModule } from '../../../test.module';
import { AttractionEventTypeUpdateComponent } from 'app/entities/attraction-event-type/attraction-event-type-update.component';
import { AttractionEventTypeService } from 'app/entities/attraction-event-type/attraction-event-type.service';
import { AttractionEventType } from 'app/shared/model/attraction-event-type.model';

describe('Component Tests', () => {
    describe('AttractionEventType Management Update Component', () => {
        let comp: AttractionEventTypeUpdateComponent;
        let fixture: ComponentFixture<AttractionEventTypeUpdateComponent>;
        let service: AttractionEventTypeService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [SmarttoursTestModule],
                declarations: [AttractionEventTypeUpdateComponent]
            })
                .overrideTemplate(AttractionEventTypeUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(AttractionEventTypeUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AttractionEventTypeService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new AttractionEventType(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.attractionEventType = entity;
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
                    const entity = new AttractionEventType();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.attractionEventType = entity;
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
