import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAttractionGroupType } from 'app/shared/model/attraction-group-type.model';
import { AttractionGroupTypeService } from './attraction-group-type.service';

@Component({
    selector: 'jhi-attraction-group-type-delete-dialog',
    templateUrl: './attraction-group-type-delete-dialog.component.html'
})
export class AttractionGroupTypeDeleteDialogComponent {
    attractionGroupType: IAttractionGroupType;

    constructor(
        private attractionGroupTypeService: AttractionGroupTypeService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.attractionGroupTypeService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'attractionGroupTypeListModification',
                content: 'Deleted an attractionGroupType'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-attraction-group-type-delete-popup',
    template: ''
})
export class AttractionGroupTypeDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ attractionGroupType }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(AttractionGroupTypeDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.attractionGroupType = attractionGroupType;
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
