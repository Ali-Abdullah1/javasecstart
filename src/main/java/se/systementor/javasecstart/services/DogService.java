package se.systementor.javasecstart.services;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import se.systementor.javasecstart.model.Dog;

import java.util.List;

@Service
public interface DogService {
    List<Dog> getPublicDogs();

    List<Dog> findAllDogs(Sort sort);

    List<Dog> findAllByDogNameContains(String name, Sort sort);
    /*
    List<Dog> findAllDogs(Sort sort);
    List<Dog> findAllByDogNameContains(String companyName, Sort sort);
     */
}