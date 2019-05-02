import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITrainingLock } from 'app/shared/model/training-lock.model';
import { TrainingLockService } from './training-lock.service';

@Component({
    selector: 'jhi-training-lock-delete-dialog',
    templateUrl: './training-lock-delete-dialog.component.html'
})
export class TrainingLockDeleteDialogComponent {
    trainingLock: ITrainingLock;

    constructor(
        private trainingLockService: TrainingLockService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.trainingLockService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'trainingLockListModification',
                content: 'Deleted an trainingLock'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-training-lock-delete-popup',
    template: ''
})
export class TrainingLockDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ trainingLock }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(TrainingLockDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.trainingLock = trainingLock;
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
