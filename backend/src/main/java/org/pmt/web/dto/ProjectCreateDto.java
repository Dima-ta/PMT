
package org.pmt.web.dto; import jakarta.validation.constraints.*; import java.time.LocalDate;
public record ProjectCreateDto(@NotBlank String name, String description, @NotNull LocalDate startDate, @NotNull Long ownerId) {}
