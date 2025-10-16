
package org.pmt.domain;
import jakarta.persistence.*; import lombok.*; import java.time.Instant; import java.time.LocalDate;
@Entity @Table(name="projects", schema="pmt") @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Project {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
  @Column(nullable=false,length=120) private String name;
  private String description;
  @Column(nullable=false) private LocalDate startDate;
  @ManyToOne(optional=false) @JoinColumn(name="owner_id") private User owner;
  @Column(nullable=false) private Instant createdAt = Instant.now();
}
