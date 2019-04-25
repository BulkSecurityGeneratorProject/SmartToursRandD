/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { SmarttoursTestModule } from '../../../test.module';
import { AttractionUpdateComponent } from 'app/entities/attraction/attraction-update.component';
import { AttractionService } from 'app/entities/attraction/attraction.service';
import { Attraction } from 'app/shared/model/attraction.model';

describe('Component Tests', () => {
    describe('Attraction Management Update Component', () => {
        let comp: AttractionUpdateComponent;
        let fixture: ComponentFixture<AttractionUpdateComponent>;
        let service: AttractionService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [SmarttoursTestModule],
                declarations: [AttractionUpdateComponent]
            })
                .overrideTemplate(AttractionUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(AttractionUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AttractionService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Attraction(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.attraction = entity;
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
                    const entity = new Attraction();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.attraction = entity;
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
