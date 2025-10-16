
import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
@Component({
  selector: 'app-projects',
  template: `
  <h2>Projects</h2>
  <form (ngSubmit)="create()">
    <input [(ngModel)]="name" name="name" placeholder="Project name" required/>
    <button type="submit">Create</button>
  </form>
  <div *ngIf="createdId">Created project #{{createdId}} — <a (click)="go()">Open tasks</a></div>
  `
})
export class ProjectsComponent {
  api = 'http://localhost:8080/api';
  name = ''; createdId?: number;
  constructor(private http: HttpClient, private router: Router) {}
  create(){
    const payload = { name: this.name, description: '', startDate: new Date().toISOString().slice(0,10), ownerId: 1 };
    this.http.post<any>(`${this.api}/projects`, payload).subscribe(p => { this.createdId = p.id; });
  }
  go(){ if(this.createdId) this.router.navigate(['/projects', this.createdId, 'tasks']); }
}
