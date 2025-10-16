
import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { TasksComponent } from './tasks.component';
import { ActivatedRoute } from '@angular/router';

describe('TasksComponent', () => {
  let httpMock: HttpTestingController;
  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [TasksComponent],
      imports: [HttpClientTestingModule, RouterTestingModule],
      providers: [{ provide: ActivatedRoute, useValue: { snapshot: { paramMap: new Map([['id','1']]) } } }]
    });
    httpMock = TestBed.inject(HttpTestingController);
  });

  it('should load lists per status', () => {
    const comp = TestBed.createComponent(TasksComponent).componentInstance;
    for (const s of comp.statuses) {
      httpMock.expectOne(`http://localhost:8080/api/projects/1/tasks?status=${s}`).flush({ content: [] });
    }
    expect(comp.tasksByStatus['TODO']).toBeDefined();
  });
});
