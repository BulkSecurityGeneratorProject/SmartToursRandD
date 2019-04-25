import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IAttractionGroupType } from 'app/shared/model/attraction-group-type.model';

type EntityResponseType = HttpResponse<IAttractionGroupType>;
type EntityArrayResponseType = HttpResponse<IAttractionGroupType[]>;

@Injectable({ providedIn: 'root' })
export class AttractionGroupTypeService {
    private resourceUrl = SERVER_API_URL + 'api/attraction-group-types';

    constructor(private http: HttpClient) {}

    create(attractionGroupType: IAttractionGroupType): Observable<EntityResponseType> {
        return this.http.post<IAttractionGroupType>(this.resourceUrl, attractionGroupType, { observe: 'response' });
    }

    update(attractionGroupType: IAttractionGroupType): Observable<EntityResponseType> {
        return this.http.put<IAttractionGroupType>(this.resourceUrl, attractionGroupType, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IAttractionGroupType>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IAttractionGroupType[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
