package io.github.vincentwuliango.taskmanagerapi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("SELECT t FROM Task t WHERE " +
            "(:status IS NULL OR :status = '' OR t.status = :status) AND " +
            "(:category IS NULL OR :category = '' OR t.category = :category)")
    List<Task> findByFilter(@Param("status") String status,
                            @Param("category") String category);
}
