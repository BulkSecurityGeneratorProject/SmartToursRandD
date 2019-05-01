import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IAttractionPurchase } from 'app/shared/model/attraction-purchase.model';
import { AttractionPurchaseService } from './attraction-purchase.service';
import { IAttraction } from 'app/shared/model/attraction.model';
import { AttractionService } from 'app/entities/attraction';

@Component({
    selector: 'jhi-attraction-purchase-update',
    templateUrl: './attraction-purchase-update.component.html'
})
export class AttractionPurchaseUpdateComponent implements OnInit {
    private _attractionPurchase: IAttractionPurchase;
    isSaving: boolean;

    attractions: IAttraction[];
    createdAt: string;
    actionTakenAt: string;

    constructor(
        private jhiAlertService: JhiAlertService,
        private attractionPurchaseService: AttractionPurchaseService,
        private attractionService: AttractionService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ attractionPurchase }) => {
            this.attractionPurchase = attractionPurchase;
        });
        this.attractionService.query().subscribe(
            (res: HttpResponse<IAttraction[]>) => {
                this.attractions = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.attractionPurchase.createdAt = moment(this.createdAt, DATE_TIME_FORMAT);
        this.attractionPurchase.actionTakenAt = moment(this.actionTakenAt, DATE_TIME_FORMAT);
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

    trackAttractionById(index: number, item: IAttraction) {
        return item.id;
    }
    get attractionPurchase() {
        return this._attractionPurchase;
    }

    set attractionPurchase(attractionPurchase: IAttractionPurchase) {
        this._attractionPurchase = attractionPurchase;
        this.createdAt = moment(attractionPurchase.createdAt).format(DATE_TIME_FORMAT);
        this.actionTakenAt = moment(attractionPurchase.actionTakenAt).format(DATE_TIME_FORMAT);
    }
}
