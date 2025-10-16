
package org.pmt.domain;
import jakarta.persistence.*; import lombok.*; import java.time.Instant;
@Entity @Table(name="task_history", schema="pmt") @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class TaskHistory {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
  private Long taskId; private Long actorId;
  @Column(nullable=false,length=64) private String field;
  @Column(columnDefinition="TEXT") private String oldValue;
  @Column(columnDefinition="TEXT") private String newValue;
  @Column(nullable=false) private Instant changedAt = Instant.now();
}
