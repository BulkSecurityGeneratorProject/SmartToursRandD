import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IAttraction } from 'app/shared/model/attraction.model';

@Component({
    selector: 'jhi-attraction-detail',
    templateUrl: './attraction-detail.component.html'
})
export class AttractionDetailComponent implements OnInit {
    attraction: IAttraction;

    constructor(private dataUtils: JhiDataUtils, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ attraction }) => {
            this.attraction = attraction;
        });
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }
    previousState() {
        window.history.back();
    }
}
