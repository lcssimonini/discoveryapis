package io.builders.discoveryapis.resources;

import io.builders.discoveryapis.dto.CreateTitleDTO;
import io.builders.discoveryapis.dto.TitleDTO;
import io.builders.discoveryapis.service.TitleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/titles")
@RequiredArgsConstructor
public class TitleResource {

    private final TitleService service;

    @GetMapping
    public Page<TitleDTO> findAll(Pageable pageable) {
        return service.findAll(pageable);
    }

    @PostMapping("/movies")
    public TitleDTO createMovie(@Valid @RequestBody CreateTitleDTO createTitleDTO) {
        return service.createMovie(createTitleDTO);
    }

    @PostMapping("/series")
    public TitleDTO createSeries(@Valid @RequestBody CreateTitleDTO createTitleDTO) {
        return service.createSeries(createTitleDTO);
    }

    @PutMapping("/movies/{id}")
    public TitleDTO updateMovie(@PathVariable String id, @Valid @RequestBody CreateTitleDTO createTitleDTO) {
        return service.updateMovie(id, createTitleDTO);
    }

    @PutMapping("/series/{id}")
    public TitleDTO updateSeries(@PathVariable String id, @Valid @RequestBody CreateTitleDTO createTitleDTO) {
        return service.updateSeries(id, createTitleDTO);
    }

    @DeleteMapping("/{id}")
   public void deleteTitle(@PathVariable String id) {
        service.delete(id);
    }

    @GetMapping("/{id}")
    public TitleDTO getTitle(@PathVariable String id) {

        return service.findById(id);
    }

    @DeleteMapping()
    public void clear() {
        service.deleteAll();
    }
}
