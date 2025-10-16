
package org.pmt.service;
import lombok.RequiredArgsConstructor; import org.springframework.stereotype.Service; import org.springframework.transaction.annotation.Transactional;
import org.pmt.repository.*; import org.pmt.domain.*; import org.pmt.web.dto.*;
import java.time.Instant;
@Service @RequiredArgsConstructor public class ProjectService {
  private final ProjectRepository projects; private final UserRepository users; private final ProjectMemberRepository members;
  @Transactional public ProjectDto create(ProjectCreateDto dto){
    User owner = users.findById(dto.ownerId()).orElseThrow();
    Project p = Project.builder().name(dto.name()).description(dto.description()).startDate(dto.startDate()).owner(owner).createdAt(Instant.now()).build();
    p = projects.save(p);
    members.save(ProjectMember.builder().projectId(p.getId()).userId(owner.getId()).role(ProjectRole.ADMIN).build());
    return new ProjectDto(p.getId(), p.getName(), p.getDescription(), p.getStartDate(), p.getOwner().getId(), p.getCreatedAt());
  }
}
