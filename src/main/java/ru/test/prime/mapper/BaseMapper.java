package ru.test.prime.mapper;

import org.mapstruct.MappingTarget;
import ru.test.prime.dto.BaseRequestDTO;
import ru.test.prime.dto.BaseResponseDTO;
import ru.test.prime.model.BaseEntity;


public interface BaseMapper<E extends BaseEntity, Q extends BaseRequestDTO, R extends BaseResponseDTO> {
    E toCreate(Q q);

    E toUpdate(Q q, @MappingTarget E e);

    R toResponse(E e);
}
