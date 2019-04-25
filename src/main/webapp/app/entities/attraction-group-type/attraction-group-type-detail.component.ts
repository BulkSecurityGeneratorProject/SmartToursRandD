import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAttractionGroupType } from 'app/shared/model/attraction-group-type.model';

@Component({
    selector: 'jhi-attraction-group-type-detail',
    templateUrl: './attraction-group-type-detail.component.html'
})
export class AttractionGroupTypeDetailComponent implements OnInit {
    attractionGroupType: IAttractionGroupType;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ attractionGroupType }) => {
            this.attractionGroupType = attractionGroupType;
        });
    }

    previousState() {
        window.history.back();
    }
}
