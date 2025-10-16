
package org.pmt.web.dto; import org.pmt.domain.TaskPriority; import org.pmt.domain.TaskStatus; import java.time.*;
public record TaskUpdateDto(String title,String description,LocalDate dueDate,LocalDate endDate, TaskStatus status, TaskPriority priority) {}
