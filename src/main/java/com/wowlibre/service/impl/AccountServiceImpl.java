package com.wowlibre.service.impl;

import com.wowlibre.dto.*;
import com.wowlibre.entity.*;
import com.wowlibre.model.*;
import com.wowlibre.repository.*;
import com.wowlibre.service.*;
import org.springframework.stereotype.*;

import java.security.*;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    @Override
    public void create(AccountCreateDto request) {
        SecureRandom random = new SecureRandom();

        try {
            byte[] salt = new byte[16];
            random.nextBytes(salt);

            final String username = request.getUsername();
            byte[] verifier = EncryptionService.computeVerifier(ParamsEncrypt.trinitycore, salt, username.toUpperCase(),
                    request.getPassword());

            AccountEntity account = new AccountEntity();
            account.setSalt(salt);
            account.setVerifier(verifier);
            account.setLocked(false);
            account.setUsername(username);
            account.setEmail(request.getEmail());
            accountRepository.save(account);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
