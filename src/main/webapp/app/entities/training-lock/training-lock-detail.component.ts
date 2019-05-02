import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITrainingLock } from 'app/shared/model/training-lock.model';

@Component({
    selector: 'jhi-training-lock-detail',
    templateUrl: './training-lock-detail.component.html'
})
export class TrainingLockDetailComponent implements OnInit {
    trainingLock: ITrainingLock;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ trainingLock }) => {
            this.trainingLock = trainingLock;
        });
    }

    previousState() {
        window.history.back();
    }
}
