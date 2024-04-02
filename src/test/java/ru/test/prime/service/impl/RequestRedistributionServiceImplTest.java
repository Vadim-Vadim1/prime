package ru.test.prime.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.jdbc.Sql;
import ru.test.prime.annotation.IT;
import ru.test.prime.integration.IntegrationTestBase;
import ru.test.prime.model.enums.TaskStatus;
import ru.test.prime.model.enums.UserStatus;
import ru.test.prime.repository.UserRepository;
import ru.test.prime.repository.projections.UserCountTaskProjections;
import ru.test.prime.service.RequestRedistributionService;

import java.util.List;


@IT
@Sql(value = "classpath:dataBefore.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "classpath:dataAfter.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class RequestRedistributionServiceImplTest extends IntegrationTestBase {
    private static final Long ID = 1L;
    @Autowired
    private RequestRedistributionService requestRedistributionService;
    @Autowired
    private UserRepository userRepository;

    @Test
    void redistributeTasks() {
        List<UserCountTaskProjections> u1 = userRepository.findUserCountTaskProjectionsByStatus(UserStatus.ONLINE, TaskStatus.OPEN, Sort.unsorted());

        requestRedistributionService.redistributeTasks(ID);

        List<UserCountTaskProjections> u2 = userRepository.findUserCountTaskProjectionsByStatus(UserStatus.ONLINE, TaskStatus.OPEN, Sort.unsorted());
        System.out.println(" before\t\t\t| after");
        for (int i = 0; i < u1.size(); i++) {
            var a1 = u1.get(i);
            var a2 = u2.get(i);
            System.out.printf("id = %s count - %s \t\t|id = %s count - %s\n",
                    a1.getUser().getId(), a1.getTaskCount(),
                    a2.getUser().getId(), a2.getTaskCount());
        }
    }
}