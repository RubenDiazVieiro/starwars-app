
import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CharactersComponent } from '../characters/characters.component';
import { PlanetsComponent } from '../planets/planets.component';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, CharactersComponent, PlanetsComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent {
  activeTab: 'characters' | 'planets' = 'characters';

  setActiveTab(tab: 'characters' | 'planets'): void {
    this.activeTab = tab;
  }
}
