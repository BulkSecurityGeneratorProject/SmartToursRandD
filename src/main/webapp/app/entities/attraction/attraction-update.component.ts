import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { IAttraction } from 'app/shared/model/attraction.model';
import { AttractionService } from './attraction.service';
import { IAttractionGroupType } from 'app/shared/model/attraction-group-type.model';
import { AttractionGroupTypeService } from 'app/entities/attraction-group-type';
import { IAttractionEventType } from 'app/shared/model/attraction-event-type.model';
import { AttractionEventTypeService } from 'app/entities/attraction-event-type';

@Component({
    selector: 'jhi-attraction-update',
    templateUrl: './attraction-update.component.html'
})
export class AttractionUpdateComponent implements OnInit {
    private _attraction: IAttraction;
    isSaving: boolean;

    attractiongrouptypes: IAttractionGroupType[];

    attractioneventtypes: IAttractionEventType[];
    openTime: string;
    closeTime: string;

    constructor(
        private dataUtils: JhiDataUtils,
        private jhiAlertService: JhiAlertService,
        private attractionService: AttractionService,
        private attractionGroupTypeService: AttractionGroupTypeService,
        private attractionEventTypeService: AttractionEventTypeService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ attraction }) => {
            this.attraction = attraction;
        });
        this.attractionGroupTypeService.query().subscribe(
            (res: HttpResponse<IAttractionGroupType[]>) => {
                this.attractiongrouptypes = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.attractionEventTypeService.query().subscribe(
            (res: HttpResponse<IAttractionEventType[]>) => {
                this.attractioneventtypes = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    setFileData(event, entity, field, isImage) {
        this.dataUtils.setFileData(event, entity, field, isImage);
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.attraction.openTime = moment(this.openTime, DATE_TIME_FORMAT);
        this.attraction.closeTime = moment(this.closeTime, DATE_TIME_FORMAT);
        if (this.attraction.id !== undefined) {
            this.subscribeToSaveResponse(this.attractionService.update(this.attraction));
        } else {
            this.subscribeToSaveResponse(this.attractionService.create(this.attraction));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IAttraction>>) {
        result.subscribe((res: HttpResponse<IAttraction>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackAttractionGroupTypeById(index: number, item: IAttractionGroupType) {
        return item.id;
    }

    trackAttractionEventTypeById(index: number, item: IAttractionEventType) {
        return item.id;
    }

    getSelected(selectedVals: Array<any>, option: any) {
        if (selectedVals) {
            for (let i = 0; i < selectedVals.length; i++) {
                if (option.id === selectedVals[i].id) {
                    return selectedVals[i];
                }
            }
        }
        return option;
    }
    get attraction() {
        return this._attraction;
    }

    set attraction(attraction: IAttraction) {
        this._attraction = attraction;
        this.openTime = moment(attraction.openTime).format(DATE_TIME_FORMAT);
        this.closeTime = moment(attraction.closeTime).format(DATE_TIME_FORMAT);
    }
}
