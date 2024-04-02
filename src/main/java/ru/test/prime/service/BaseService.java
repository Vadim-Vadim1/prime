package ru.test.prime.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.test.prime.dto.BaseRequestDTO;
import ru.test.prime.dto.BaseResponseDTO;
import ru.test.prime.model.BaseEntity;

import java.io.Serializable;

public interface BaseService<
        E extends BaseEntity,
        ID extends Serializable,
        Q extends BaseRequestDTO,
        R extends BaseResponseDTO
        > {

    R getById(ID id);

    Page<R> getAll(Pageable pageable);

    Long save(Q request);

    void update(ID id, Q request);

    void delete(ID id);
}
