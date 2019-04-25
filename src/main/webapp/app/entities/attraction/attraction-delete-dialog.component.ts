import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAttraction } from 'app/shared/model/attraction.model';
import { AttractionService } from './attraction.service';

@Component({
    selector: 'jhi-attraction-delete-dialog',
    templateUrl: './attraction-delete-dialog.component.html'
})
export class AttractionDeleteDialogComponent {
    attraction: IAttraction;

    constructor(private attractionService: AttractionService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.attractionService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'attractionListModification',
                content: 'Deleted an attraction'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-attraction-delete-popup',
    template: ''
})
export class AttractionDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ attraction }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(AttractionDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.attraction = attraction;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
