import { Component, OnInit, OnDestroy, inject } from '@angular/core';
import { Planet } from '../../models/planet.model';
import { PlanetsService, PlanetFilters } from '../../services/planets.service';
import { InternalPageResponse } from '../../services/base-api.service';
import { DatePipe } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { Subject, takeUntil } from 'rxjs';

@Component({
  selector: 'app-planets',
  standalone: true,
  imports: [DatePipe, FormsModule, RouterModule],
  templateUrl: './planets.component.html',
  styleUrls: ['./planets.component.scss']
})
export class PlanetsComponent implements OnInit, OnDestroy {
  private planetsService = inject(PlanetsService);
  private destroy$ = new Subject<void>();

  hasError = false;
  errorMessage = '';
  isEmpty = false;
  planets: Planet[] = [];

  currentPage = 0;
  pageSize = 15;
  totalItems = 0;
  searchTerm = '';
  sortField = 'name';
  sortDirection: 'asc' | 'desc' = 'asc';

  ngOnInit(): void {
    this.loadPlanets();
  }

  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }

  loadPlanets(): void {
    this.hasError = false;
    this.errorMessage = '';

    const filters: PlanetFilters = {
      page: this.currentPage,
      size: this.pageSize,
      name: this.searchTerm,
      sortBy: this.sortField,
      sortDirection: this.sortDirection
    };

    this.planetsService.searchPlanets(filters)
      .pipe(takeUntil(this.destroy$))
      .subscribe({
        next: (response: InternalPageResponse<Planet>) => {
          this.planets = response.data;
          this.totalItems = response.total;
          this.isEmpty = response.data.length === 0;
        },
        error: () => {
          this.hasError = true;
          this.errorMessage = 'Unable to access planetary database. Please try again.\n';
        }
      });
  }

  onSearchChange(): void {
    this.currentPage = 0;
    this.loadPlanets();
  }

  changeSort(field: string): void {
    if (this.sortField === field) {
      this.sortDirection = this.sortDirection === 'asc' ? 'desc' : 'asc';
    } else {
      this.sortField = field;
      this.sortDirection = 'asc';
    }

    this.currentPage = 0;
    this.loadPlanets();
  }

  nextPage(): void {
    if (this.currentPage < this.getTotalPages() - 1) {
      this.currentPage++;
      this.loadPlanets();
    }
  }

  prevPage(): void {
    if (this.currentPage > 0) {
      this.currentPage--;
      this.loadPlanets();
    }
  }

  goToPage(page: number): void {
    if (page >= 0 && page < this.getTotalPages()) {
      this.currentPage = page;
      this.loadPlanets();
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
