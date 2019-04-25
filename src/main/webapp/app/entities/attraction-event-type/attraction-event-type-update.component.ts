import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IAttractionEventType } from 'app/shared/model/attraction-event-type.model';
import { AttractionEventTypeService } from './attraction-event-type.service';
import { IAttraction } from 'app/shared/model/attraction.model';
import { AttractionService } from 'app/entities/attraction';
import { IAttractionPurchase } from 'app/shared/model/attraction-purchase.model';
import { AttractionPurchaseService } from 'app/entities/attraction-purchase';

@Component({
    selector: 'jhi-attraction-event-type-update',
    templateUrl: './attraction-event-type-update.component.html'
})
export class AttractionEventTypeUpdateComponent implements OnInit {
    private _attractionEventType: IAttractionEventType;
    isSaving: boolean;

    attractions: IAttraction[];

    attractionpurchases: IAttractionPurchase[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private attractionEventTypeService: AttractionEventTypeService,
        private attractionService: AttractionService,
        private attractionPurchaseService: AttractionPurchaseService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ attractionEventType }) => {
            this.attractionEventType = attractionEventType;
        });
        this.attractionService.query().subscribe(
            (res: HttpResponse<IAttraction[]>) => {
                this.attractions = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.attractionPurchaseService.query().subscribe(
            (res: HttpResponse<IAttractionPurchase[]>) => {
                this.attractionpurchases = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.attractionEventType.id !== undefined) {
            this.subscribeToSaveResponse(this.attractionEventTypeService.update(this.attractionEventType));
        } else {
            this.subscribeToSaveResponse(this.attractionEventTypeService.create(this.attractionEventType));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IAttractionEventType>>) {
        result.subscribe((res: HttpResponse<IAttractionEventType>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackAttractionById(index: number, item: IAttraction) {
        return item.id;
    }

    trackAttractionPurchaseById(index: number, item: IAttractionPurchase) {
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
    get attractionEventType() {
        return this._attractionEventType;
    }

    set attractionEventType(attractionEventType: IAttractionEventType) {
        this._attractionEventType = attractionEventType;
    }
}
