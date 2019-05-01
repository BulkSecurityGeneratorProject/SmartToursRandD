import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IAttractionPurchase } from 'app/shared/model/attraction-purchase.model';

type EntityResponseType = HttpResponse<IAttractionPurchase>;
type EntityArrayResponseType = HttpResponse<IAttractionPurchase[]>;

@Injectable({ providedIn: 'root' })
export class AttractionPurchaseService {
    private resourceUrl = SERVER_API_URL + 'api/attraction-purchases';

    constructor(private http: HttpClient) {}

    create(attractionPurchase: IAttractionPurchase): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(attractionPurchase);
        return this.http
            .post<IAttractionPurchase>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(attractionPurchase: IAttractionPurchase): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(attractionPurchase);
        return this.http
            .put<IAttractionPurchase>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IAttractionPurchase>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IAttractionPurchase[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(attractionPurchase: IAttractionPurchase): IAttractionPurchase {
        const copy: IAttractionPurchase = Object.assign({}, attractionPurchase, {
            createdAt:
                attractionPurchase.createdAt != null && attractionPurchase.createdAt.isValid()
                    ? attractionPurchase.createdAt.toJSON()
                    : null,
            actionTakenAt:
                attractionPurchase.actionTakenAt != null && attractionPurchase.actionTakenAt.isValid()
                    ? attractionPurchase.actionTakenAt.toJSON()
                    : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.createdAt = res.body.createdAt != null ? moment(res.body.createdAt) : null;
        res.body.actionTakenAt = res.body.actionTakenAt != null ? moment(res.body.actionTakenAt) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((attractionPurchase: IAttractionPurchase) => {
            attractionPurchase.createdAt = attractionPurchase.createdAt != null ? moment(attractionPurchase.createdAt) : null;
            attractionPurchase.actionTakenAt = attractionPurchase.actionTakenAt != null ? moment(attractionPurchase.actionTakenAt) : null;
        });
        return res;
    }
}
