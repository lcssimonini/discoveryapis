package io.builders.discoveryapis.service;

import io.builders.discoveryapis.dto.CreateTitleDTO;
import io.builders.discoveryapis.dto.TitleDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TitleService {

    Page<TitleDTO> findAll(Pageable pageable);

    TitleDTO createMovie(CreateTitleDTO createTitleDTO);

    TitleDTO createSeries(CreateTitleDTO createTitleDTO);

    TitleDTO updateSeries(String id, CreateTitleDTO createTitleDTO);

    TitleDTO updateMovie(String id, CreateTitleDTO createTitleDTO);

    void delete(String id);

    TitleDTO findById(String id);

    void deleteAll();
}
