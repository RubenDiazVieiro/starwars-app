
import { Component, OnInit, OnDestroy, inject } from '@angular/core';
import { Character } from '../../models/character.model';
import { CharacterService, CharacterFilters } from '../../services/character.service';
import { InternalPageResponse } from '../../services/base-api.service';
import { DatePipe } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { Subject, takeUntil } from 'rxjs';

@Component({
  selector: 'app-characters',
  standalone: true,
  imports: [DatePipe, FormsModule, RouterModule],
  templateUrl: './characters.component.html',
  styleUrls: ['./characters.component.scss']
})
export class CharactersComponent implements OnInit, OnDestroy {
  private characterService = inject(CharacterService);
  private destroy$ = new Subject<void>();

  hasError = false;
  errorMessage = '';
  isEmpty = false;
  characters: Character[] = [];

  currentPage = 0;
  pageSize = 15;
  totalItems = 0;
  searchTerm = '';
  sortField = 'name';
  sortDirection: 'asc' | 'desc' = 'asc';

  ngOnInit(): void {
    this.loadCharacters();
  }

  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }

  loadCharacters(): void {
    this.hasError = false;
    this.errorMessage = '';

    const filters: CharacterFilters = {
      page: this.currentPage,
      size: this.pageSize,
      name: this.searchTerm,
      sortBy: this.sortField,
      sortDirection: this.sortDirection
    };

    this.characterService.searchCharacters(filters)
      .pipe(takeUntil(this.destroy$))
      .subscribe({
        next: (response: InternalPageResponse<Character>) => {
          this.characters = response.data;
          this.totalItems = response.total;
          this.isEmpty = response.data.length === 0;
        },
        error: () => {
          this.hasError = true;
          this.errorMessage = 'Connection to the galactic network failed. Please try again.';
        }
      });
  }

  onSearchChange(): void {
    this.currentPage = 0;
    this.loadCharacters();
  }

  changeSort(field: string): void {
    if (this.sortField === field) {
      this.sortDirection = this.sortDirection === 'asc' ? 'desc' : 'asc';
    } else {
      this.sortField = field;
      this.sortDirection = 'asc';
    }

    this.currentPage = 0;
    this.loadCharacters();
  }

  nextPage(): void {
    if (this.currentPage < this.getTotalPages() - 1) {
      this.currentPage++;
      this.loadCharacters();
    }
  }

  prevPage(): void {
    if (this.currentPage > 0) {
      this.currentPage--;
      this.loadCharacters();
    }
  }

  goToPage(page: number): void {
    if (page >= 0 && page < this.getTotalPages()) {
      this.currentPage = page;
      this.loadCharacters();
    }
  }

  getTotalPages(): number {
    return Math.ceil(this.totalItems / this.pageSize);
  }

  getPageNumbers(): number[] {
    const totalPages = this.getTotalPages();
    const maxVisible = 5;
    const pages: number[] = [];

    if (totalPages <= maxVisible) {
      for (let i = 0; i < totalPages; i++) {
        pages.push(i);
      }
    } else {
      const start = Math.max(0, this.currentPage - 2);
      const end = Math.min(totalPages - 1, start + maxVisible - 1);

      for (let i = start; i <= end; i++) {
        pages.push(i);
      }
    }

    return pages;
  }

  getSortDirection(field: string): string {
    if (this.sortField === `${field},asc`) return 'asc';
    if (this.sortField === `${field},desc`) return 'desc';
    return 'none';
  }

  getSortIcon(field: string): string {
    if (this.sortField !== field) return '▲▼';
    return this.sortDirection === 'asc' ? '▲' : '▼';
  }
}
