package ru.test.prime.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.test.prime.model.Task;
import ru.test.prime.model.enums.TaskStatus;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("""
            SELECT t FROM Task t 
            JOIN FETCH t.responsible u 
            WHERE t.status = :taskStatus AND u.id =:id
            """)
    List<Task> findByRequestStatusAndUserId(
            @Param("taskStatus") TaskStatus taskStatus,
            @Param("id") Long id);
    @Query("SELECT t FROM Task t JOIN FETCH t.responsible")
    Page<Task> findPageBy(Pageable pageable);

}
