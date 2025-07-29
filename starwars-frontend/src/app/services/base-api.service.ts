import { HttpClient, HttpParams } from '@angular/common/http';
import {inject, Injectable} from '@angular/core';
import {Observable, map, throwError, catchError} from 'rxjs';
import { environment } from '../../environments/environment';

export interface ExternalPageResponse<T> {
  content: T[];
  page: {
    totalElements: number;
    totalPages: number;
    size: number;
    number: number;
  };
}

export interface InternalPageResponse<T> {
  data: T[];
  total: number;
  totalPages: number;
  currentPage: number;
  pageSize: number;
}

export interface QueryParams {
  page?: number;
  size?: number;
  name?: string;
  sortBy?: string;
  sortDirection?: 'asc' | 'desc';
}

@Injectable({ providedIn: 'root' })
export abstract class BaseApiService<T> {

  protected abstract readonly apiUrl: string;
  protected readonly baseUrl = environment.apiUrl;
  protected readonly http = inject(HttpClient);

  findAll(params: QueryParams = {}): Observable<InternalPageResponse<T>> {
    const finalParams = {
      ...params,
      page: params.page ?? 0,
      size: params.size ?? 15
    };

    const httpParams = this.buildHttpParams(finalParams);

    return this.http.get<ExternalPageResponse<T>>(`${this.baseUrl}/${this.apiUrl}`, { params: httpParams })
      .pipe(
        map(backendResponse => this.transformResponse(backendResponse)),
        catchError(error => {
          console.error(`Error fetching ${this.apiUrl}:`, error);
          return throwError(() => error);
        })
      );
  }

  private transformResponse(backendResponse: ExternalPageResponse<T>): InternalPageResponse<T> {
    return {
      data: backendResponse.content,
      total: backendResponse.page.totalElements,
      totalPages: backendResponse.page.totalPages,
      currentPage: backendResponse.page.number,
      pageSize: backendResponse.page.size
    };
  }

  private buildHttpParams(params: QueryParams): HttpParams {
    let httpParams = new HttpParams();

    httpParams = httpParams.set('page', params.page!.toString());
    httpParams = httpParams.set('size', params.size!.toString());

    if (params.name?.trim()) {
      httpParams = httpParams.set('name', params.name.trim());
    }

    if (params.sortBy && params.sortDirection) {
      httpParams = httpParams.set('sort', `${params.sortBy},${params.sortDirection}`);
    }

    return httpParams;
  }
}

