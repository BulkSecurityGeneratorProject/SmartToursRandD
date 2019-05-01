import { Moment } from 'moment';
import { IAttraction } from 'app/shared/model//attraction.model';

export interface IAttractionPurchase {
    id?: number;
    traveling?: string;
    activity?: string;
    userDistance?: number;
    userLatitude?: number;
    userLongitude?: number;
    createdAt?: Moment;
    actionTakenAt?: Moment;
    actionTaken?: boolean;
    attraction?: IAttraction;
}

export class AttractionPurchase implements IAttractionPurchase {
    constructor(
        public id?: number,
        public traveling?: string,
        public activity?: string,
        public userDistance?: number,
        public userLatitude?: number,
        public userLongitude?: number,
        public createdAt?: Moment,
        public actionTakenAt?: Moment,
        public actionTaken?: boolean,
        public attraction?: IAttraction
    ) {
        this.actionTaken = false;
    }
}
