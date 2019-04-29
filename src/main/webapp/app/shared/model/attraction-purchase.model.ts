import { IAttractionGroupType } from 'app/shared/model//attraction-group-type.model';
import { IAttractionEventType } from 'app/shared/model//attraction-event-type.model';
import { IAttraction } from 'app/shared/model//attraction.model';

export interface IAttractionPurchase {
    id?: number;
    userLatitude?: number;
    userLongitude?: number;
    userDistance?: number;
    purchased?: boolean;
    groupTypes?: IAttractionGroupType[];
    eventTypes?: IAttractionEventType[];
    attraction?: IAttraction;
}

export class AttractionPurchase implements IAttractionPurchase {
    constructor(
        public id?: number,
        public userLatitude?: number,
        public userLongitude?: number,
        public userDistance?: number,
        public purchased?: boolean,
        public groupTypes?: IAttractionGroupType[],
        public eventTypes?: IAttractionEventType[],
        public attraction?: IAttraction
    ) {
        this.purchased = false;
    }
}
