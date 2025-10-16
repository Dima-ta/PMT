
package org.pmt.domain;
import jakarta.persistence.*; import lombok.*; import java.time.Instant;
@Entity @Table(name="notifications", schema="pmt") @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Notification {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
  private Long userId;
  @Column(nullable=false,length=40) private String type;
  @Column(columnDefinition="TEXT") private String payload;
  @Column(nullable=false) private boolean isRead = false;
  @Column(nullable=false) private Instant createdAt = Instant.now();
}
