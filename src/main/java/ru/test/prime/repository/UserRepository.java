package ru.test.prime.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.test.prime.model.User;
import ru.test.prime.model.enums.TaskStatus;
import ru.test.prime.model.enums.UserStatus;
import ru.test.prime.repository.projections.UserCountTaskProjections;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

@Query("""
        SELECT u as user, COUNT(t) as taskCount
        FROM User u
        LEFT JOIN u.userTask t 
        WHERE u.userStatus = :user_status AND (t.status = :task_status OR t.id IS NULL) 
        GROUP BY u
        """)
    List<UserCountTaskProjections> findUserCountTaskProjectionsByStatus(
            @Param("user_status") UserStatus userStatus,
            @Param("task_status") TaskStatus taskStatus,
            Sort sort);
}
