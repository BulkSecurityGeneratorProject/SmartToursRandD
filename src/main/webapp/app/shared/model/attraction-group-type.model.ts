import { IAttraction } from 'app/shared/model//attraction.model';
import { IAttractionPurchase } from 'app/shared/model//attraction-purchase.model';

export const enum GroupType {
    NONE = 'NONE',
    KIDS = 'KIDS',
    ADULTS = 'ADULTS'
}

export interface IAttractionGroupType {
    id?: number;
    groupType?: GroupType;
    attractions?: IAttraction[];
    purchases?: IAttractionPurchase[];
}

export class AttractionGroupType implements IAttractionGroupType {
    constructor(
        public id?: number,
        public groupType?: GroupType,
        public attractions?: IAttraction[],
        public purchases?: IAttractionPurchase[]
    ) {}
}
