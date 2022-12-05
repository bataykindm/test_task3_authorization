package bdm.test.service;

import bdm.test.entity.Account;
import bdm.test.exception.AccountNotFoundException;
import bdm.test.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account getAccountById(Long id) {
        Account foundedAccount = accountRepository.findById(id).orElseThrow(AccountNotFoundException::new);
        log.info("IN getAccountById - account: {} has been found by Id: {}", foundedAccount, id);
        return foundedAccount;
    }

    public Account getAccountByUsername(String username) {
        Account foundedAccount = accountRepository.findByUsername(username).orElseThrow(AccountNotFoundException::new);
        log.info("IN getAccountById - account: {} has been found by username: {}", foundedAccount, username);
        return foundedAccount;
    }
}
