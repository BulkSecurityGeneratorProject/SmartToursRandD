import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IAttractionGroupType } from 'app/shared/model/attraction-group-type.model';
import { AttractionGroupTypeService } from './attraction-group-type.service';
import { IAttraction } from 'app/shared/model/attraction.model';
import { AttractionService } from 'app/entities/attraction';
import { IAttractionPurchase } from 'app/shared/model/attraction-purchase.model';
import { AttractionPurchaseService } from 'app/entities/attraction-purchase';

@Component({
    selector: 'jhi-attraction-group-type-update',
    templateUrl: './attraction-group-type-update.component.html'
})
export class AttractionGroupTypeUpdateComponent implements OnInit {
    private _attractionGroupType: IAttractionGroupType;
    isSaving: boolean;

    attractions: IAttraction[];

    attractionpurchases: IAttractionPurchase[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private attractionGroupTypeService: AttractionGroupTypeService,
        private attractionService: AttractionService,
        private attractionPurchaseService: AttractionPurchaseService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ attractionGroupType }) => {
            this.attractionGroupType = attractionGroupType;
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
        if (this.attractionGroupType.id !== undefined) {
            this.subscribeToSaveResponse(this.attractionGroupTypeService.update(this.attractionGroupType));
        } else {
            this.subscribeToSaveResponse(this.attractionGroupTypeService.create(this.attractionGroupType));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IAttractionGroupType>>) {
        result.subscribe((res: HttpResponse<IAttractionGroupType>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
    get attractionGroupType() {
        return this._attractionGroupType;
    }

    set attractionGroupType(attractionGroupType: IAttractionGroupType) {
        this._attractionGroupType = attractionGroupType;
    }
}
