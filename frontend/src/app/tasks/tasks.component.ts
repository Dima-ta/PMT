
import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
@Component({
  selector: 'app-tasks',
  styles: [`.board{display:flex;gap:16px}.col{width:25%}.card{border:1px solid #ddd;padding:8px;margin:8px 0}`],
  template: `
  <h2>Tasks of project {{pid}}</h2>
  <form (ngSubmit)="create()">
    <input [(ngModel)]="title" name="title" placeholder="Task title" required/>
    <button type="submit">Add</button>
  </form>

  <div class="board">
    <div class="col" *ngFor="let s of statuses">
      <h3>{{s}}</h3>
      <div class="card" *ngFor="let t of (tasksByStatus[s]||[])">
        <b>{{t.title}}</b><br/>{{t.description||'-'}}<br/>
        <button (click)="move(t.id, nextStatus(s))">Move → {{nextStatus(s)}}</button>
      </div>
    </div>
  </div>
  `
})
export class TasksComponent {
  api = 'http://localhost:8080/api';
  pid = 0; title='';
  statuses = ['TODO','IN_PROGRESS','DONE','BLOCKED'] as const;
  tasksByStatus: Record<string, any[]> = {};
  constructor(private http: HttpClient, route: ActivatedRoute){
    this.pid = Number(route.snapshot.paramMap.get('id'));
    this.refresh();
  }
  refresh(){
    this.tasksByStatus = {};
    for(const s of this.statuses){
      this.http.get<any>(`${this.api}/projects/${this.pid}/tasks?status=${s}`).subscribe(page => {
        this.tasksByStatus[s] = page.content || [];
      });
    }
  }
  create(){
    const payload = { title: this.title, description: '' };
    this.http.post(`${this.api}/projects/${this.pid}/tasks?creatorId=1`, payload).subscribe(()=> this.refresh());
  }
  nextStatus(s: string){ return s==='TODO'?'IN_PROGRESS': s==='IN_PROGRESS'?'DONE':'DONE'; }
  move(id: number, st: string){
    this.http.patch(`${this.api}/projects/${this.pid}/tasks/${id}?actorId=1`, { status: st }).subscribe(()=> this.refresh());
  }
}
