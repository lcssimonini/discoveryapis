package io.builders.discoveryapis.resources;

import io.builders.discoveryapis.domain.Title;
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

    @PostMapping("/movie")
    public TitleDTO createMovie(@Valid @RequestBody CreateTitleDTO createTitleDTO) {
        return service.createMovie(createTitleDTO);
    }

    @PostMapping("/series")
    public TitleDTO createSeries(@Valid @RequestBody CreateTitleDTO createTitleDTO) {
        return service.createSeries(createTitleDTO);
    }

    @PutMapping("/{id}")
    public TitleDTO updateClient(@PathVariable String id, @Valid @RequestBody CreateTitleDTO createTitleDTO) {
        return service.updateTitle(id, createTitleDTO);
    }

    @DeleteMapping("/{id}")
   public void deleteClient(@PathVariable String id) {
        service.delete(id);
    }

    @GetMapping("/{id}")
    public TitleDTO getClient(@PathVariable String id) {
        return service.findById(id);
    }
}
