
package org.pmt.domain;
import lombok.*; import java.io.Serializable;
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode
public class ProjectMemberId implements Serializable { private Long projectId; private Long userId; }
