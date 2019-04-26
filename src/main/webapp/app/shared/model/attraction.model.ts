import { Moment } from 'moment';
import { IAttractionGroupType } from 'app/shared/model//attraction-group-type.model';
import { IAttractionEventType } from 'app/shared/model//attraction-event-type.model';

export interface IAttraction {
    id?: number;
    sygicTravelId?: string;
    rating?: number;
    lat?: number;
    lng?: number;
    name?: string;
    marker?: string;
    perex?: any;
    thumbnailUrl?: string;
    categories?: string;
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
        public sygicTravelId?: string,
        public rating?: number,
        public lat?: number,
        public lng?: number,
        public name?: string,
        public marker?: string,
        public perex?: any,
        public thumbnailUrl?: string,
        public categories?: string,
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
