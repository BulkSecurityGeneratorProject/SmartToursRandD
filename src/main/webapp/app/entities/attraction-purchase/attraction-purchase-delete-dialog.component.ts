import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAttractionPurchase } from 'app/shared/model/attraction-purchase.model';
import { AttractionPurchaseService } from './attraction-purchase.service';

@Component({
    selector: 'jhi-attraction-purchase-delete-dialog',
    templateUrl: './attraction-purchase-delete-dialog.component.html'
})
export class AttractionPurchaseDeleteDialogComponent {
    attractionPurchase: IAttractionPurchase;

    constructor(
        private attractionPurchaseService: AttractionPurchaseService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.attractionPurchaseService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'attractionPurchaseListModification',
                content: 'Deleted an attractionPurchase'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-attraction-purchase-delete-popup',
    template: ''
})
export class AttractionPurchaseDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ attractionPurchase }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(AttractionPurchaseDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.attractionPurchase = attractionPurchase;
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
