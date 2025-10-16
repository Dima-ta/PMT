
package org.pmt.web;
import lombok.RequiredArgsConstructor; import org.springframework.data.domain.*; import org.springframework.web.bind.annotation.*;
import org.pmt.domain.TaskStatus; import org.pmt.service.TaskService; import org.pmt.web.dto.*;
@RestController @RequestMapping("/api/projects/{projectId}/tasks") @RequiredArgsConstructor public class TaskController {
  private final TaskService service;
  @PostMapping public TaskDto create(@PathVariable Long projectId, @RequestParam Long creatorId, @RequestBody TaskCreateDto dto){ return service.create(projectId, creatorId, dto); }
  @GetMapping public Page<TaskDto> list(@PathVariable Long projectId, @RequestParam(required=false) TaskStatus status, Pageable pageable){ return service.list(projectId, status, pageable); }
  @PatchMapping("/{taskId}") public TaskDto update(@PathVariable Long projectId, @PathVariable Long taskId, @RequestParam Long actorId, @RequestBody TaskUpdateDto dto){ return service.update(taskId, actorId, dto); }
}
