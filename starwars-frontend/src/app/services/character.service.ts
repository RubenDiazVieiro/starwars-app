import { Injectable } from '@angular/core';
import { Observable, map } from 'rxjs';
import { Character } from '../models/character.model';
import { ApiCharacter } from '../models/api-character.model';
import { BaseApiService, InternalPageResponse, QueryParams } from './base-api.service';

export interface CharacterFilters extends QueryParams {}

@Injectable({ providedIn: 'root' })
export class CharacterService extends BaseApiService<ApiCharacter> {
  protected readonly apiUrl = 'characters';

  searchCharacters(filters: CharacterFilters = {}): Observable<InternalPageResponse<Character>> {
    return super.findAll(filters).pipe(
      map(response => ({
        ...response,
        data: response.data.map((character: ApiCharacter) => this.transformCharacter(character))
      }))
    );
  }

  private transformCharacter(character: ApiCharacter): Character {
    return {
      name: character.name,
      gender: character.gender,
      height: character.height,
      mass: character.mass,
      hairColor: character.hair_color,
      eyeColor: character.eye_color,
      skinColor: character.skin_color,
      filmsCount: character.films?.length || 0,
      created: new Date(character.created)
    };
  }

}
