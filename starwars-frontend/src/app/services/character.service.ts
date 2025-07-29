import { Injectable } from '@angular/core';
import { Observable, map } from 'rxjs';
import { Character } from '../models/character.model';
import { BaseApiService, InternalPageResponse, QueryParams } from './base-api.service';

export interface CharacterFilters extends QueryParams {}

@Injectable({ providedIn: 'root' })
export class CharacterService extends BaseApiService<Character> {
  protected readonly apiUrl = 'characters';

  searchCharacters(filters: CharacterFilters = {}): Observable<InternalPageResponse<Character>> {
    return super.findAll(filters).pipe(
      map(response => ({
        ...response,
        data: response.data.map((character: any) => this.transformCharacter(character))
      }))
    );
  }

  private transformCharacter(character: any): Character {
    return {
      ...character,
      hairColor: character.hair_color,
      eyeColor: character.eye_color,
      skinColor: character.skin_color,
      filmsCount: character.films?.length || 0,
      created: new Date(character.created)
    };

  }

}
