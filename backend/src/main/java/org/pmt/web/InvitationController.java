
package org.pmt.web;
import lombok.RequiredArgsConstructor; import org.springframework.web.bind.annotation.*; import org.pmt.service.InvitationService; import org.pmt.web.dto.*;
@RestController @RequestMapping("/api/projects/{projectId}/invitations") @RequiredArgsConstructor public class InvitationController {
  private final InvitationService service;
  @PostMapping public InvitationDto create(@PathVariable Long projectId, @RequestBody InvitationCreateDto dto){ return service.create(projectId, dto); }
  @PostMapping("/accept/{token}") public void accept(@PathVariable Long projectId, @PathVariable String token){ service.accept(token); }
}
