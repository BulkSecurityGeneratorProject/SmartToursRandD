import { IAttraction } from 'app/shared/model//attraction.model';
import { IAttractionPurchase } from 'app/shared/model//attraction-purchase.model';

export const enum EventType {
    NONE = 'NONE',
    GET_ACTIVE = 'GET_ACTIVE',
    RELAX = 'RELAX'
}

export interface IAttractionEventType {
    id?: number;
    eventType?: EventType;
    attractions?: IAttraction[];
    purchases?: IAttractionPurchase[];
}

export class AttractionEventType implements IAttractionEventType {
    constructor(
        public id?: number,
        public eventType?: EventType,
        public attractions?: IAttraction[],
        public purchases?: IAttractionPurchase[]
    ) {}
}
