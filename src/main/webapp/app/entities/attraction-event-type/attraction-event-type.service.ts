import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IAttractionEventType } from 'app/shared/model/attraction-event-type.model';

type EntityResponseType = HttpResponse<IAttractionEventType>;
type EntityArrayResponseType = HttpResponse<IAttractionEventType[]>;

@Injectable({ providedIn: 'root' })
export class AttractionEventTypeService {
    private resourceUrl = SERVER_API_URL + 'api/attraction-event-types';

    constructor(private http: HttpClient) {}

    create(attractionEventType: IAttractionEventType): Observable<EntityResponseType> {
        return this.http.post<IAttractionEventType>(this.resourceUrl, attractionEventType, { observe: 'response' });
    }

    update(attractionEventType: IAttractionEventType): Observable<EntityResponseType> {
        return this.http.put<IAttractionEventType>(this.resourceUrl, attractionEventType, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IAttractionEventType>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IAttractionEventType[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
