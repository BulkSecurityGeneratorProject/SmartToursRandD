/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { SmarttoursTestModule } from '../../../test.module';
import { AttractionGroupTypeUpdateComponent } from 'app/entities/attraction-group-type/attraction-group-type-update.component';
import { AttractionGroupTypeService } from 'app/entities/attraction-group-type/attraction-group-type.service';
import { AttractionGroupType } from 'app/shared/model/attraction-group-type.model';

describe('Component Tests', () => {
    describe('AttractionGroupType Management Update Component', () => {
        let comp: AttractionGroupTypeUpdateComponent;
        let fixture: ComponentFixture<AttractionGroupTypeUpdateComponent>;
        let service: AttractionGroupTypeService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [SmarttoursTestModule],
                declarations: [AttractionGroupTypeUpdateComponent]
            })
                .overrideTemplate(AttractionGroupTypeUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(AttractionGroupTypeUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AttractionGroupTypeService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new AttractionGroupType(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.attractionGroupType = entity;
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
                    const entity = new AttractionGroupType();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.attractionGroupType = entity;
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
