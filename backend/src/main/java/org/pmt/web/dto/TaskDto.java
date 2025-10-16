
package org.pmt.web.dto; import org.pmt.domain.*; import java.time.*;
public record TaskDto(Long id,Long projectId,String title,String description,LocalDate dueDate,LocalDate endDate,TaskStatus status,TaskPriority priority,Long createdBy,Instant createdAt,Instant updatedAt) {}
