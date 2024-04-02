package ru.test.prime.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.test.prime.dto.UserRequestDTO;
import ru.test.prime.dto.UserResponseDTO;
import ru.test.prime.mapper.BaseMapper;
import ru.test.prime.model.User;
import ru.test.prime.model.enums.UserStatus;
import ru.test.prime.repository.UserRepository;
import ru.test.prime.service.BaseServiceImpl;
import ru.test.prime.service.RequestRedistributionService;
import ru.test.prime.service.UserService;

@Service
@Transactional(readOnly = true)
@Slf4j
public class UserServiceImpl extends
        BaseServiceImpl<User,
                Long,
                UserRequestDTO,
                UserResponseDTO,
                UserRepository>
        implements UserService {
    private final RequestRedistributionService requestRedistributionService;

    @Autowired
    public UserServiceImpl(UserRepository repository,
                           BaseMapper<User, UserRequestDTO,
                                   UserResponseDTO> mapper,
                           RequestRedistributionService requestRedistributionService) {
        super(repository, mapper);
        this.requestRedistributionService = requestRedistributionService;
    }

    @Override
    public void update(Long userId, UserRequestDTO request) {
        super.update(userId, request);
        if (request.getUserStatus() == UserStatus.OFFLINE) {
            requestRedistributionService.redistributeTasks(userId);
        }
    }

    @Override
    public Page<UserResponseDTO> getAll(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toResponse);
    }
}
