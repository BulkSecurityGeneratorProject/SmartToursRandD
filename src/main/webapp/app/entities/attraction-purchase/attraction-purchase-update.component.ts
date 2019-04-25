import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IAttractionPurchase } from 'app/shared/model/attraction-purchase.model';
import { AttractionPurchaseService } from './attraction-purchase.service';
import { IAttractionGroupType } from 'app/shared/model/attraction-group-type.model';
import { AttractionGroupTypeService } from 'app/entities/attraction-group-type';
import { IAttractionEventType } from 'app/shared/model/attraction-event-type.model';
import { AttractionEventTypeService } from 'app/entities/attraction-event-type';

@Component({
    selector: 'jhi-attraction-purchase-update',
    templateUrl: './attraction-purchase-update.component.html'
})
export class AttractionPurchaseUpdateComponent implements OnInit {
    private _attractionPurchase: IAttractionPurchase;
    isSaving: boolean;

    attractiongrouptypes: IAttractionGroupType[];

    attractioneventtypes: IAttractionEventType[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private attractionPurchaseService: AttractionPurchaseService,
        private attractionGroupTypeService: AttractionGroupTypeService,
        private attractionEventTypeService: AttractionEventTypeService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ attractionPurchase }) => {
            this.attractionPurchase = attractionPurchase;
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

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.attractionPurchase.id !== undefined) {
            this.subscribeToSaveResponse(this.attractionPurchaseService.update(this.attractionPurchase));
        } else {
            this.subscribeToSaveResponse(this.attractionPurchaseService.create(this.attractionPurchase));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IAttractionPurchase>>) {
        result.subscribe((res: HttpResponse<IAttractionPurchase>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
    get attractionPurchase() {
        return this._attractionPurchase;
    }

    set attractionPurchase(attractionPurchase: IAttractionPurchase) {
        this._attractionPurchase = attractionPurchase;
    }
}
