
package org.pmt.domain;
import jakarta.persistence.*; import lombok.*; import java.time.Instant;
@Entity @Table(name="users", schema="pmt") @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class User {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
  @Column(nullable=false,unique=true,length=50) private String username;
  @Column(nullable=false,unique=true,length=255) private String email;
  @Column(nullable=false,length=255) private String passwordHash;
  @Column(nullable=false) private Instant createdAt = Instant.now();
}
