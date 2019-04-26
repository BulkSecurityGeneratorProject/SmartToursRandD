import { IAttractionGroupType } from 'app/shared/model//attraction-group-type.model';
import { IAttractionEventType } from 'app/shared/model//attraction-event-type.model';

export interface IAttractionPurchase {
    id?: number;
    attractionId?: number;
    userDistance?: number;
    weatherCategory?: string;
    purchased?: boolean;
    groupTypes?: IAttractionGroupType[];
    eventTypes?: IAttractionEventType[];
}

export class AttractionPurchase implements IAttractionPurchase {
    constructor(
        public id?: number,
        public attractionId?: number,
        public userDistance?: number,
        public weatherCategory?: string,
        public purchased?: boolean,
        public groupTypes?: IAttractionGroupType[],
        public eventTypes?: IAttractionEventType[]
    ) {
        this.purchased = false;
    }
}
