import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ITrainingLock } from 'app/shared/model/training-lock.model';

type EntityResponseType = HttpResponse<ITrainingLock>;
type EntityArrayResponseType = HttpResponse<ITrainingLock[]>;

@Injectable({ providedIn: 'root' })
export class TrainingLockService {
    private resourceUrl = SERVER_API_URL + 'api/training-locks';

    constructor(private http: HttpClient) {}

    create(trainingLock: ITrainingLock): Observable<EntityResponseType> {
        return this.http.post<ITrainingLock>(this.resourceUrl, trainingLock, { observe: 'response' });
    }

    update(trainingLock: ITrainingLock): Observable<EntityResponseType> {
        return this.http.put<ITrainingLock>(this.resourceUrl, trainingLock, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ITrainingLock>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ITrainingLock[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
