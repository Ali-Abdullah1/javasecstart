package se.systementor.javasecstart.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.systementor.javasecstart.model.Account;
import se.systementor.javasecstart.model.AccountRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;


    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public void register(String username, String password, String email) throws IllegalArgumentException{



        if (accountRepository.findByUsername(username) != null){
            throw new IllegalArgumentException("namn används");
        }

        if(accountRepository.findByEmail(email) !=null){
            throw new IllegalArgumentException("mejl används");
        }
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(passwordEncoder.encode(password));
        account.setEmail(email);
        accountRepository.save(account);
    }

}



