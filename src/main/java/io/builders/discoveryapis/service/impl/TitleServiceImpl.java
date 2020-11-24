package io.builders.discoveryapis.service.impl;

import io.builders.discoveryapis.domain.Title;
import io.builders.discoveryapis.domain.Type;
import io.builders.discoveryapis.dto.CreateTitleDTO;
import io.builders.discoveryapis.dto.TitleDTO;
import io.builders.discoveryapis.repository.TitleRepository;
import io.builders.discoveryapis.service.TitleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.stream.Collectors;

import static io.builders.discoveryapis.domain.Type.MOVIE;
import static io.builders.discoveryapis.domain.Type.SERIES;

@Slf4j
@Service
@RequiredArgsConstructor
public class TitleServiceImpl implements TitleService {

    private final TitleRepository repository;

    @Override
    public Page<TitleDTO> findAll(Pageable pageable) {
        Page<Title> titlePage = repository.findAll(pageable);
        return getClientDTOPage(pageable, titlePage);
    }

    @Override
    public TitleDTO createMovie(CreateTitleDTO createTitleDTO) {
        log.info("Attempt to save movie: {}", createTitleDTO);
        Title movie = Title.from(createTitleDTO);
        movie.setType(MOVIE);
        return TitleDTO.from(repository.save(movie));
    }

    @Override
    public TitleDTO createSeries(CreateTitleDTO createTitleDTO) {
        log.info("Attempt to save series: {}", createTitleDTO);
        Title series = Title.from(createTitleDTO);
        series.setType(SERIES);
        return TitleDTO.from(repository.save(series));
    }

    @Override
    public TitleDTO updateSeries(String id, CreateTitleDTO createTitleDTO) {
        log.info("Attempt to update series with id: {}, and data: {}", id, createTitleDTO);
        return updateTitle(id, createTitleDTO, SERIES);
    }

    @Override
    public TitleDTO updateMovie(String id, CreateTitleDTO createTitleDTO) {
        log.info("Attempt to update movie with id: {}, and data: {}", id, createTitleDTO);
        return updateTitle(id, createTitleDTO, MOVIE);
    }

    private TitleDTO updateTitle(String id, CreateTitleDTO createTitleDTO, Type type) {
        if (repository.existsById(id)) {
            Title title = Title.from(createTitleDTO);
            title.setId(id);
            title.setType(type);
            return TitleDTO.from(repository.save(title));
        } else {
            throw new TitleNotFoundException(id);
        }
    }

    @Override
    public void delete(String id) {
        log.info("Attempt to delete title with id: {}", id);
        repository.deleteById(id);
    }

    @Override
    public TitleDTO findById(String id) {
        log.info("Attempt to find Title with id: {}", id);
        return repository.findById(id)
                .map(TitleDTO::from)
                .orElseThrow(() -> new TitleNotFoundException(id));
    }

    @Override
    public void deleteAll() {
        log.info("removing all titles");
        repository.deleteAll();
    }

    private PageImpl<TitleDTO> getClientDTOPage(Pageable pageable, Page<Title> clientPage) {
        return new PageImpl<>(
                clientPage.getContent().stream().map(TitleDTO::from).collect(Collectors.toList()),
                pageable,
                clientPage.getTotalElements());
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Title does not exist")
    public static class TitleNotFoundException extends RuntimeException {

        public TitleNotFoundException(String id) {
            super(String.format("Title with id %s does not exist", id));
        }
    }
}
