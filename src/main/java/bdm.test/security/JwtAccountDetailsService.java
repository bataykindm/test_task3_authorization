package bdm.test.security;

import bdm.test.entity.Account;
import bdm.test.security.jwt.JwtAccount;
import bdm.test.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class JwtAccountDetailsService implements UserDetailsService {

    private final AccountService accountService;

    @Autowired
    public JwtAccountDetailsService(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        Account account = accountService.getAccountByUsername(username);
        JwtAccount jwtAccount = new JwtAccount(account);
        return jwtAccount;
    }
}
