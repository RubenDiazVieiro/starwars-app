import { Injectable } from '@angular/core';
import { Observable, map } from 'rxjs';
import { Planet } from '../models/planet.model';
import { BaseApiService, InternalPageResponse, QueryParams } from './base-api.service';

export interface PlanetFilters extends QueryParams {}

@Injectable({ providedIn: 'root' })
export class PlanetsService extends BaseApiService<Planet> {
  protected readonly apiUrl = 'planets';

  searchPlanets(filters: PlanetFilters = {}): Observable<InternalPageResponse<Planet>> {
    return super.findAll(filters).pipe(
      map(response => ({
        ...response,
        data: response.data.map((planet: any) => this.transformPlanet(planet))
      }))
    );
  }

  private transformPlanet(planet: any): Planet {
    return {
      ...planet,
      residentsCount: planet.residents?.length || 0,
      filmsCount: planet.films?.length || 0,
      created: new Date(planet.created)
    };
  }
}
