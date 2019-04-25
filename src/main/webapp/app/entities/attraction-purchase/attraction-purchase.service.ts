import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

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
        return this.http.post<IAttractionPurchase>(this.resourceUrl, attractionPurchase, { observe: 'response' });
    }

    update(attractionPurchase: IAttractionPurchase): Observable<EntityResponseType> {
        return this.http.put<IAttractionPurchase>(this.resourceUrl, attractionPurchase, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IAttractionPurchase>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IAttractionPurchase[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
