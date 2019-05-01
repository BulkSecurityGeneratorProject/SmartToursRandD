import { Moment } from 'moment';
import { IAttractionPurchase } from 'app/shared/model//attraction-purchase.model';

export interface IAttraction {
    id?: number;
    sygicTravelId?: string;
    rating?: number;
    latitude?: number;
    longitude?: number;
    name?: string;
    marker?: string;
    perex?: any;
    thumbnailUrl?: string;
    categories?: string;
    dsSummary?: any;
    dsIcon?: string;
    dsApparentTemperatureHigh?: number;
    dsApparentTemperatureLow?: number;
    dsDewPoint?: number;
    dsHumidity?: number;
    dsPressure?: number;
    dsWindSpeed?: number;
    dsWindGust?: number;
    dsCloudCover?: number;
    dsVisibility?: number;
    adultPrice?: number;
    childPrice?: number;
    accessible?: boolean;
    facilities?: boolean;
    openTime?: Moment;
    closeTime?: Moment;
    attractionPurchases?: IAttractionPurchase[];
}

export class Attraction implements IAttraction {
    constructor(
        public id?: number,
        public sygicTravelId?: string,
        public rating?: number,
        public latitude?: number,
        public longitude?: number,
        public name?: string,
        public marker?: string,
        public perex?: any,
        public thumbnailUrl?: string,
        public categories?: string,
        public dsSummary?: any,
        public dsIcon?: string,
        public dsApparentTemperatureHigh?: number,
        public dsApparentTemperatureLow?: number,
        public dsDewPoint?: number,
        public dsHumidity?: number,
        public dsPressure?: number,
        public dsWindSpeed?: number,
        public dsWindGust?: number,
        public dsCloudCover?: number,
        public dsVisibility?: number,
        public adultPrice?: number,
        public childPrice?: number,
        public accessible?: boolean,
        public facilities?: boolean,
        public openTime?: Moment,
        public closeTime?: Moment,
        public attractionPurchases?: IAttractionPurchase[]
    ) {
        this.accessible = false;
        this.facilities = false;
    }
}
