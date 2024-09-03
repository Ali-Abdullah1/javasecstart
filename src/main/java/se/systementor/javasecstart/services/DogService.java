    package se.systementor.javasecstart.services;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.data.domain.Sort;
    import org.springframework.stereotype.Service;
    import se.systementor.javasecstart.model.Dog;
    import se.systementor.javasecstart.model.DogRepository;

    import java.util.List;
    import java.util.Optional;

    @Service
    public abstract class DogService {
        @Autowired
        DogRepository dogRepository;

        public List<Dog> getPublicDogs(){
            return dogRepository.findAllBySoldToIsNull();
        }

        public Optional<Dog> getDogById(int id) {
            return dogRepository.findById((long) id);
        }

        public void saveDog(Dog dog) {
            dogRepository.save(dog);
        }


        public abstract List<Dog> findAllDogs(Sort sort);

        public abstract List<Dog> findAllByDogNameContains(String name, Sort sort);
    }
