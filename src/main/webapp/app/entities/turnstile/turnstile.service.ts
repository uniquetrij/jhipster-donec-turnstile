import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ITurnstile } from 'app/shared/model/turnstile.model';

type EntityResponseType = HttpResponse<ITurnstile>;
type EntityArrayResponseType = HttpResponse<ITurnstile[]>;

@Injectable({ providedIn: 'root' })
export class TurnstileService {
  public resourceUrl = SERVER_API_URL + 'api/turnstiles';

  constructor(protected http: HttpClient) {}

  create(turnstile: ITurnstile): Observable<EntityResponseType> {
    return this.http.post<ITurnstile>(this.resourceUrl, turnstile, { observe: 'response' });
  }

  update(turnstile: ITurnstile): Observable<EntityResponseType> {
    return this.http.put<ITurnstile>(this.resourceUrl, turnstile, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITurnstile>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITurnstile[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
