import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAttractionEventType } from 'app/shared/model/attraction-event-type.model';

@Component({
    selector: 'jhi-attraction-event-type-detail',
    templateUrl: './attraction-event-type-detail.component.html'
})
export class AttractionEventTypeDetailComponent implements OnInit {
    attractionEventType: IAttractionEventType;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ attractionEventType }) => {
            this.attractionEventType = attractionEventType;
        });
    }

    previousState() {
        window.history.back();
    }
}
