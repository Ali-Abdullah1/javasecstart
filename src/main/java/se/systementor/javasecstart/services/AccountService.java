package se.systementor.javasecstart.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.systementor.javasecstart.model.AccountRepository;

@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;
}
