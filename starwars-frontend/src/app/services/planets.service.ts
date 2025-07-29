
import { Injectable } from '@angular/core';
import { Observable, map } from 'rxjs';
import { Planet } from '../models/planet.model';
import { ApiPlanet } from '../models/api-planet.model';
import { BaseApiService, InternalPageResponse, QueryParams } from './base-api.service';

export interface PlanetFilters extends QueryParams {}

@Injectable({ providedIn: 'root' })
export class PlanetsService extends BaseApiService<ApiPlanet> {
  protected readonly apiUrl = 'planets';

  searchPlanets(filters: PlanetFilters = {}): Observable<InternalPageResponse<Planet>> {
    return super.findAll(filters).pipe(
      map(response => ({
        ...response,
        data: response.data.map((planet: ApiPlanet) => this.transformPlanet(planet))
      }))
    );
  }

  private transformPlanet(planet: ApiPlanet): Planet {
    return {
      name: planet.name,
      climate: planet.climate,
      terrain: planet.terrain,
      population: planet.population,
      residentsCount: planet.residents?.length || 0,
      filmsCount: planet.films?.length || 0,
      created: new Date(planet.created)
    };
  }

}
