
package org.pmt.service;
import lombok.RequiredArgsConstructor; import org.springframework.stereotype.Service; import org.springframework.transaction.annotation.Transactional;
import org.pmt.repository.*; import org.pmt.domain.*; import org.pmt.web.dto.*; import java.time.*; import java.util.*;
@Service @RequiredArgsConstructor public class InvitationService {
  private final InvitationRepository invitations; private final ProjectRepository projects;
  @Transactional public InvitationDto create(Long projectId, InvitationCreateDto dto){
    projects.findById(projectId).orElseThrow();
    String token = UUID.randomUUID().toString().replace("-","");
    Invitation inv = Invitation.builder().projectId(projectId).email(dto.email()).role(dto.role()==null? ProjectRole.VIEWER: dto.role()).token(token).status("PENDING").createdAt(Instant.now()).expiresAt(Instant.now().plusSeconds(60*60*24*7)).build();
    inv = invitations.save(inv);
    return new InvitationDto(inv.getId(), inv.getProjectId(), inv.getEmail(), inv.getRole(), inv.getToken(), inv.getStatus(), inv.getCreatedAt(), inv.getExpiresAt());
  }
  @Transactional public void accept(String token){ Invitation inv = invitations.findByToken(token).orElseThrow(); inv.setStatus("ACCEPTED"); }
}
