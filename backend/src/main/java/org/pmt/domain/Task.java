
package org.pmt.domain;
import jakarta.persistence.*; import lombok.*; import java.time.Instant; import java.time.LocalDate;
@Entity @Table(name="tasks", schema="pmt") @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Task {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
  @ManyToOne(optional=false) @JoinColumn(name="project_id") private Project project;
  @Column(nullable=false,length=160) private String title;
  @Column(columnDefinition="TEXT") private String description;
  private LocalDate dueDate; private LocalDate endDate;
  @Enumerated(EnumType.STRING) @Column(nullable=false) private TaskStatus status = TaskStatus.TODO;
  @Enumerated(EnumType.STRING) @Column(nullable=false) private TaskPriority priority = TaskPriority.MEDIUM;
  @ManyToOne(optional=false) @JoinColumn(name="created_by") private User createdBy;
  @Column(nullable=false) private Instant updatedAt = Instant.now();
  @Column(nullable=false) private Instant createdAt = Instant.now();
}
