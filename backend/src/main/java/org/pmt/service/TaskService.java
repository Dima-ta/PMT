
package org.pmt.service;
import lombok.RequiredArgsConstructor; import org.springframework.stereotype.Service; import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.*; import java.time.Instant;
import org.pmt.repository.*; import org.pmt.domain.*; import org.pmt.web.dto.*;
@Service @RequiredArgsConstructor public class TaskService {
  private final TaskRepository tasks; private final ProjectRepository projects; private final UserRepository users; private final TaskHistoryRepository histories; private final NotificationRepository notifications;
  @Transactional public TaskDto create(Long projectId, Long creatorId, TaskCreateDto dto){
    Project project = projects.findById(projectId).orElseThrow(); User creator = users.findById(creatorId).orElseThrow();
    Task t = Task.builder().project(project).title(dto.title()).description(dto.description()).dueDate(dto.dueDate()).status(TaskStatus.TODO).priority(TaskPriority.MEDIUM).createdBy(creator).createdAt(Instant.now()).updatedAt(Instant.now()).build();
    t = tasks.save(t); histories.save(TaskHistory.builder().taskId(t.getId()).actorId(creator.getId()).field("CREATE").newValue(t.getTitle()).build());
    return toDto(t);
  }
  @Transactional public TaskDto update(Long taskId, Long actorId, TaskUpdateDto dto){
    Task t = tasks.findById(taskId).orElseThrow();
    if (dto.title()!=null){ histories.save(TaskHistory.builder().taskId(t.getId()).actorId(actorId).field("title").oldValue(t.getTitle()).newValue(dto.title()).build()); t.setTitle(dto.title()); }
    if (dto.description()!=null){ histories.save(TaskHistory.builder().taskId(t.getId()).actorId(actorId).field("description").oldValue(t.getDescription()).newValue(dto.description()).build()); t.setDescription(dto.description()); }
    if (dto.dueDate()!=null){ t.setDueDate(dto.dueDate()); }
    if (dto.endDate()!=null){ t.setEndDate(dto.endDate()); }
    if (dto.status()!=null){ t.setStatus(dto.status()); }
    if (dto.priority()!=null){ t.setPriority(dto.priority()); }
    t.setUpdatedAt(Instant.now()); return toDto(t);
  }
  @Transactional(readOnly=true) public Page<TaskDto> list(Long projectId, TaskStatus status, Pageable pageable){
    return (status==null? tasks.findByProjectId(projectId,pageable): tasks.findByProjectIdAndStatus(projectId,status,pageable)).map(this::toDto);
  }
  private TaskDto toDto(Task t){ return new TaskDto(t.getId(), t.getProject().getId(), t.getTitle(), t.getDescription(), t.getDueDate(), t.getEndDate(), t.getStatus(), t.getPriority(), t.getCreatedBy().getId(), t.getCreatedAt(), t.getUpdatedAt()); }
}
