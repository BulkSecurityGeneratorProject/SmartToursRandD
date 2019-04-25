import { Moment } from 'moment';
import { IAttractionGroupType } from 'app/shared/model//attraction-group-type.model';
import { IAttractionEventType } from 'app/shared/model//attraction-event-type.model';

export interface IAttraction {
    id?: number;
    name?: string;
    subtitle?: string;
    description?: any;
    latitude?: number;
    longitude?: number;
    adultPrice?: number;
    childPrice?: number;
    accessible?: boolean;
    facilities?: boolean;
    openTime?: Moment;
    closeTime?: Moment;
    groupTypes?: IAttractionGroupType[];
    eventTypes?: IAttractionEventType[];
}

export class Attraction implements IAttraction {
    constructor(
        public id?: number,
        public name?: string,
        public subtitle?: string,
        public description?: any,
        public latitude?: number,
        public longitude?: number,
        public adultPrice?: number,
        public childPrice?: number,
        public accessible?: boolean,
        public facilities?: boolean,
        public openTime?: Moment,
        public closeTime?: Moment,
        public groupTypes?: IAttractionGroupType[],
        public eventTypes?: IAttractionEventType[]
    ) {
        this.accessible = false;
        this.facilities = false;
    }
}
