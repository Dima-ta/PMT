
package org.pmt.domain;
import jakarta.persistence.*; import lombok.*; import java.time.Instant;
@Entity @Table(name="invitations", schema="pmt") @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Invitation {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
  private Long projectId;
  @Column(nullable=false) private String email;
  @Enumerated(EnumType.STRING)
  @Column(nullable=false) private ProjectRole role = ProjectRole.VIEWER;
  @Column(nullable=false,unique=true,length=64) private String token;
  @Column(nullable=false,length=16) private String status = "PENDING";
  @Column(nullable=false) private Instant createdAt = Instant.now();
  @Column(nullable=false) private Instant expiresAt;
}
