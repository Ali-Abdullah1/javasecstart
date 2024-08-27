package se.systementor.javasecstart.model;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DogRepository extends CrudRepository<Dog, Long> {

    List<Dog> findAllBySoldToIsNull();


    @Query("Select c from Dog c where c.id = :id")
    Optional<Dog> getDogById(@Param("id") Long id);
    List<Dog> findAll(Sort sort);
    List<Dog> findAllByNameContains(String name, Sort sort);
}