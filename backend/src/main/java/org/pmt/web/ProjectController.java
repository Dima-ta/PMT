
package org.pmt.web;
import lombok.RequiredArgsConstructor; import org.springframework.web.bind.annotation.*; import org.pmt.service.ProjectService; import org.pmt.web.dto.*;
@RestController @RequestMapping("/api/projects") @RequiredArgsConstructor public class ProjectController {
  private final ProjectService service;
  @PostMapping public ProjectDto create(@RequestBody ProjectCreateDto dto){ return service.create(dto); }
}
