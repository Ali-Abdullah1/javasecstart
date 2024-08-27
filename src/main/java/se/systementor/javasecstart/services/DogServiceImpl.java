package se.systementor.javasecstart.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import se.systementor.javasecstart.model.Dog;
import se.systementor.javasecstart.model.DogRepository;

import java.text.Collator;
import java.util.List;
import java.util.Locale;

@Service
public class DogServiceImpl implements DogService {
    @Autowired
    DogRepository dogRepository;

    @Override
    public List<Dog> getPublicDogs(){
        return dogRepository.findAllBySoldToIsNull();
    }

    /*
    private final DogRepository dogRepository;

    public DogServiceImpl(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    @Override
    public List<Dog> getPublicDogs() {
        return null;
    }

    @Override
    public List<Dog> getPublicDogs() {
        return dogRepository.findAll();
    }

    @Override
    public List<Dog> getPublicDogs() {
        return null;
    }
     */

    @Override
    public List<Dog> findAllDogs(Sort sort) {
        List<Dog> dogs = dogRepository.findAll(sort);
        Collator collator = Collator.getInstance(new Locale("sv", "SE"));

        for (Sort.Order order : sort) {
            switch (order.getProperty().toLowerCase()) {
                case "name":
                    dogs.sort((c1, c2) -> {
                        int result = collator.compare(c1.getName(), c2.getName());
                        return order.isAscending() ? result : -result;
                    });
                    break;
            }
        }

        return dogs;
    }

    @Override
    public List<Dog> findAllByDogNameContains(String name, Sort sort) {
        List<Dog> dogs = dogRepository.findAllByNameContains(name, sort);
        Collator collator = Collator.getInstance(new Locale("sv", "SE"));

        for (Sort.Order order : sort) {
            switch (order.getProperty().toLowerCase()) {
                case "name":
                    dogs.sort((c1, c2) -> {
                        int result = collator.compare(c1.getName(), c2.getName());
                        return order.isAscending() ? result : -result;
                    });
                    break;
            }
        }

        return dogs;
    }
}