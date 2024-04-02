package ru.test.prime.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.test.prime.dto.BaseRequestDTO;
import ru.test.prime.dto.BaseResponseDTO;
import ru.test.prime.mapper.BaseMapper;
import ru.test.prime.model.BaseEntity;

import java.io.Serializable;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public abstract class BaseServiceImpl<
        E extends BaseEntity,
        ID extends Serializable,
        Q extends BaseRequestDTO,
        R extends BaseResponseDTO,
        J extends JpaRepository<E, ID>
        >
        implements BaseService<E, ID, Q, R> {

    protected final J repository;
    protected final BaseMapper<E, Q, R> mapper;

    @Override
    public R getById(ID id) {
        log.debug("Getting entity by id: {}", id);
        return mapper.toResponse(repository.findById(id).orElseThrow());
    }

    @Override
    public abstract Page<R> getAll(Pageable pageable);

    @Override
    @Transactional
    public Long save(Q request) {
        log.debug("Saving entity: {}", request);
        E create = mapper.toCreate(request);
        repository.save(create);
        return create.getId();
    }

    @Transactional
    @Override
    public void update(ID id, Q request) {
        log.debug("Updating entity with id {}: {}", id, request);
        E e = repository.findById(id).orElseThrow();
        repository.save(mapper.toUpdate(request, e));

    }

    @Transactional
    @Override
    public void delete(ID id) {
        log.debug("Deleting entity with id: {}", id);
        E e = repository.findById(id).orElseThrow();
        repository.delete(e);
    }
}
