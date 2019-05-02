import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { ITrainingLock } from 'app/shared/model/training-lock.model';
import { TrainingLockService } from './training-lock.service';

@Component({
    selector: 'jhi-training-lock-update',
    templateUrl: './training-lock-update.component.html'
})
export class TrainingLockUpdateComponent implements OnInit {
    private _trainingLock: ITrainingLock;
    isSaving: boolean;

    constructor(private trainingLockService: TrainingLockService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ trainingLock }) => {
            this.trainingLock = trainingLock;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.trainingLock.id !== undefined) {
            this.subscribeToSaveResponse(this.trainingLockService.update(this.trainingLock));
        } else {
            this.subscribeToSaveResponse(this.trainingLockService.create(this.trainingLock));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ITrainingLock>>) {
        result.subscribe((res: HttpResponse<ITrainingLock>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get trainingLock() {
        return this._trainingLock;
    }

    set trainingLock(trainingLock: ITrainingLock) {
        this._trainingLock = trainingLock;
    }
}
