
package org.pmt.service;
import org.junit.jupiter.api.Test; import org.mockito.Mockito;
import org.pmt.domain.*; import org.pmt.repository.*; import org.pmt.web.dto.*;
import org.springframework.data.domain.PageImpl; import org.springframework.data.domain.PageRequest;
import java.time.LocalDate; import java.util.*; import java.util.List;
import static org.assertj.core.api.Assertions.*; import static org.mockito.ArgumentMatchers.*; import static org.mockito.Mockito.when;
public class TaskServiceTest {
  @Test void createTask_ok(){
    TaskRepository tasks = Mockito.mock(TaskRepository.class);
    ProjectRepository projects = Mockito.mock(ProjectRepository.class);
    UserRepository users = Mockito.mock(UserRepository.class);
    TaskHistoryRepository histories = Mockito.mock(TaskHistoryRepository.class);
    NotificationRepository notifications = Mockito.mock(NotificationRepository.class);
    TaskService service = new TaskService(tasks, projects, users, histories, notifications);
    User owner = User.builder().id(1L).username("alice").email("a@e.com").passwordHash("x").build();
    Project project = Project.builder().id(10L).name("P").owner(owner).build();
    when(projects.findById(10L)).thenReturn(Optional.of(project));
    when(users.findById(1L)).thenReturn(Optional.of(owner));
    when(tasks.save(any(Task.class))).thenAnswer(inv->inv.getArgument(0));
    var out = service.create(10L,1L,new TaskCreateDto("T","D", LocalDate.now()));
    assertThat(out.title()).isEqualTo("T"); assertThat(out.projectId()).isEqualTo(10L);
  }
  @Test void listTasks_empty(){
    TaskRepository tasks = Mockito.mock(TaskRepository.class);
    TaskService service = new TaskService(tasks, null, null, null, null);
    when(tasks.findByProjectId(eq(10L), any(PageRequest.class))).thenReturn(new PageImpl<>(List.of()));
    var page = service.list(10L, null, PageRequest.of(0,20));
    assertThat(page.getTotalElements()).isEqualTo(0);
  }
}
