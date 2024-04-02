package ru.test.prime.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.test.prime.model.Task;
import ru.test.prime.model.enums.TaskStatus;
import ru.test.prime.model.enums.UserStatus;
import ru.test.prime.repository.TaskRepository;
import ru.test.prime.repository.UserRepository;
import ru.test.prime.service.RequestRedistributionService;
import ru.test.prime.service.util.RandomNumberGenerator;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class RequestRedistributionServiceImpl implements RequestRedistributionService {

    private final TaskRepository taskRepository;
    private final RandomNumberGenerator numberGenerator;
    private final UserRepository userRepository;

    @Transactional
    @Override
    public void redistributeTasks(Long offlineUserId) {
        log.info("Starting task redistribution for offline user with id {}", offlineUserId);

        Set<UserProbability> sortedUsersWithProbability =
                userRepository.findUserCountTaskProjectionsByStatus(
                                UserStatus.ONLINE, TaskStatus.OPEN, Sort.unsorted()
                        )
                        .stream()
                        .map(e -> new UserProbability(e.getUser(), e.getTaskCount()))
                        .collect(
                                Collectors.toCollection(
                                        () -> new TreeSet<>(
                                                Comparator.comparingLong(UserProbability::getCountTask).reversed()
                                        )
                                )
                        );

        if (sortedUsersWithProbability.isEmpty()) {
            log.info("No online users with open tasks found. Exiting redistribution.");
            return;
        }

        List<Task> openTasksForOfflineUser =
                taskRepository.findByRequestStatusAndUserId(TaskStatus.OPEN,offlineUserId);

        if (openTasksForOfflineUser.isEmpty()) {
            log.info("No open tasks for offline user with id {}. Exiting redistribution.", offlineUserId);
            return;
        }

        double totalProbability = calcTotalProbability(sortedUsersWithProbability);

        redistributeTasks(openTasksForOfflineUser, sortedUsersWithProbability, totalProbability);

        log.info("Task redistribution for offline user with id {} completed successfully", offlineUserId);

        taskRepository.saveAll(openTasksForOfflineUser);
    }

    private double calcTotalProbability(Set<UserProbability> sortedUsersWithProbability) {
        return sortedUsersWithProbability.stream().mapToDouble(UserProbability::getProbability).sum();
    }

    private void redistributeTasks(List<Task> openTasksForOfflineUser,
                                   Set<UserProbability> onlineUsersWithTaskCounts,
                                   double totalProbability) {
        for (Task t : openTasksForOfflineUser) {
            UserProbability user = selectRandomUser(onlineUsersWithTaskCounts, totalProbability);

            totalProbability -= user.getProbability();

            t.setResponsible(user.getUser());

            log.info("Task {} redistributed to user {}.", t.getId(), user.getUser().getId());

            onlineUsersWithTaskCounts.remove(user);

            user.setCountTask(user.getCountTask() + 1);

            onlineUsersWithTaskCounts.add(user);

            totalProbability += user.getProbability();

        }
    }

    private UserProbability selectRandomUser(Set<UserProbability> onlineUsersWithTaskCounts,
                                             double totalProbability) {
        double randomProbability = numberGenerator.generateRandomDouble(totalProbability);
        double cumulativeProbability = 0;
        UserProbability user = null;

        for (UserProbability u : onlineUsersWithTaskCounts) {
            cumulativeProbability += u.getProbability();
            user = u;
            if (randomProbability < cumulativeProbability) {
                break;
            }
        }

        log.debug("Selected user {} with task count {}.", user.getUser().getId(), user.getCountTask());
        return user;
    }
}

