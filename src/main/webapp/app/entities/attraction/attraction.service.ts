import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IAttraction } from 'app/shared/model/attraction.model';

type EntityResponseType = HttpResponse<IAttraction>;
type EntityArrayResponseType = HttpResponse<IAttraction[]>;

@Injectable({ providedIn: 'root' })
export class AttractionService {
    private resourceUrl = SERVER_API_URL + 'api/attractions';

    constructor(private http: HttpClient) {}

    create(attraction: IAttraction): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(attraction);
        return this.http
            .post<IAttraction>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(attraction: IAttraction): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(attraction);
        return this.http
            .put<IAttraction>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IAttraction>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IAttraction[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(attraction: IAttraction): IAttraction {
        const copy: IAttraction = Object.assign({}, attraction, {
            openTime: attraction.openTime != null && attraction.openTime.isValid() ? attraction.openTime.toJSON() : null,
            closeTime: attraction.closeTime != null && attraction.closeTime.isValid() ? attraction.closeTime.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.openTime = res.body.openTime != null ? moment(res.body.openTime) : null;
        res.body.closeTime = res.body.closeTime != null ? moment(res.body.closeTime) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((attraction: IAttraction) => {
            attraction.openTime = attraction.openTime != null ? moment(attraction.openTime) : null;
            attraction.closeTime = attraction.closeTime != null ? moment(attraction.closeTime) : null;
        });
        return res;
    }
}
