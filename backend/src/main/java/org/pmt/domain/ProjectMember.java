
package org.pmt.domain;
import jakarta.persistence.*; import lombok.*; import java.time.Instant;
@Entity @Table(name="project_members", schema="pmt") @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder @IdClass(ProjectMemberId.class)
public class ProjectMember {
  @Id private Long projectId;
  @Id private Long userId;
  @Enumerated(EnumType.STRING) @Column(nullable=false) private ProjectRole role;
  @Column(nullable=false) private Instant addedAt = Instant.now();
}
