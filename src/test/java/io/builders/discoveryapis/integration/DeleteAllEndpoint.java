package io.builders.discoveryapis.integration;

import io.builders.discoveryapis.repository.TitleRepository;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@TestConfiguration
@RestController
@RequestMapping("/clear")
public class DeleteAllEndpoint {

    private final TitleRepository repository;

    public DeleteAllEndpoint(TitleRepository repository) {
        this.repository = repository;
    }

    @DeleteMapping
    public void clear() {
        repository.deleteAll();
    }
}
