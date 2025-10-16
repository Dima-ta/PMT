
package org.pmt.repository;
import org.pmt.domain.*; import org.springframework.data.domain.*; import org.springframework.data.jpa.repository.JpaRepository;
public interface TaskRepository extends JpaRepository<Task, Long> {
  Page<Task> findByProjectId(Long projectId, Pageable pageable);
  Page<Task> findByProjectIdAndStatus(Long projectId, TaskStatus status, Pageable pageable);
}
