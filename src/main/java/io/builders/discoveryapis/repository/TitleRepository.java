package io.builders.discoveryapis.repository;

import io.builders.discoveryapis.domain.Title;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TitleRepository extends MongoRepository<Title, String> {
}
