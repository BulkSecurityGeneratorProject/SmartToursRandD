import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAttractionEventType } from 'app/shared/model/attraction-event-type.model';
import { AttractionEventTypeService } from './attraction-event-type.service';

@Component({
    selector: 'jhi-attraction-event-type-delete-dialog',
    templateUrl: './attraction-event-type-delete-dialog.component.html'
})
export class AttractionEventTypeDeleteDialogComponent {
    attractionEventType: IAttractionEventType;

    constructor(
        private attractionEventTypeService: AttractionEventTypeService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.attractionEventTypeService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'attractionEventTypeListModification',
                content: 'Deleted an attractionEventType'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-attraction-event-type-delete-popup',
    template: ''
})
export class AttractionEventTypeDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ attractionEventType }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(AttractionEventTypeDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.attractionEventType = attractionEventType;
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
