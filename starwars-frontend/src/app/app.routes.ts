import { Routes } from '@angular/router';
import { CharactersComponent } from './components/characters/characters.component';
import { PlanetsComponent } from './components/planets/planets.component';
import { WelcomeComponent } from './components/welcome/welcome.component';

export const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' }, // ✅ Redirige / a /home
  { path: 'home', component: WelcomeComponent }, // ✅ /home muestra Welcome
  { path: 'characters', component: CharactersComponent },
  { path: 'planets', component: PlanetsComponent },
  { path: '**', redirectTo: '/home' } // ✅ Cualquier ruta inválida va a /home
];
