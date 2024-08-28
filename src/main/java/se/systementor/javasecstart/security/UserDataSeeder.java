package se.systementor.javasecstart.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDataSeeder {
    @Autowired
    UserRepo userRepository;


    public void seedUsers() {
        if (userRepository.getByUsername("farzad.jalili@hotmail.se") == null) {
            addUser("farzad.jalili@hotmail.se", "farzad", "1");
        }
        if (userRepository.getByUsername("ahnaf.khan@gmail.se") == null) {
            addUser("ahnaf.khan@gmail.se", "Ahnaf", "2");
        }
        if (userRepository.getByUsername("alina.mazic@hotmail.se") == null) {
            addUser("alina.mazic@hotmail.se", "alina", "3");
        }
    }

    private void addUser(String mail,String firstName, String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hash = encoder.encode(password);
        User user = User.builder().enabled(true).password(hash).username(mail).firstName(firstName).build();
        userRepository.save(user);
    }

}