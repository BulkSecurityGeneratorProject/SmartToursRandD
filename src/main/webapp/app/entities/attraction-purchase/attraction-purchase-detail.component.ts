import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAttractionPurchase } from 'app/shared/model/attraction-purchase.model';

@Component({
    selector: 'jhi-attraction-purchase-detail',
    templateUrl: './attraction-purchase-detail.component.html'
})
export class AttractionPurchaseDetailComponent implements OnInit {
    attractionPurchase: IAttractionPurchase;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ attractionPurchase }) => {
            this.attractionPurchase = attractionPurchase;
        });
    }

    previousState() {
        window.history.back();
    }
}
