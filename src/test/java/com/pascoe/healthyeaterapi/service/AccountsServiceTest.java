package com.pascoe.healthyeaterapi.service;

import com.pascoe.healthyeaterapi.model.UserAccount;
import com.pascoe.healthyeaterapi.repository.UserAccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountsServiceTest {

    @Mock
    private UserAccountRepository userAccountRepository;
    @Mock
    private UserAccount mockAccount;
    @InjectMocks
    private AccountsService accountsService;

    private static final String TEST_EMAIL_ADDRESS = "testing@gmail.com";

    @BeforeEach
    void setUp() {
        when(mockAccount.getEmailAddress()).thenReturn(TEST_EMAIL_ADDRESS);
    }

    @Test
    void createAccount() {
        when(userAccountRepository.save(Mockito.any(UserAccount.class))).thenReturn(mockAccount);

        UserAccount account = accountsService.createAccount(mockAccount);
        assertThat(account).isNotNull();
        assertThat(account.getEmailAddress()).isEqualTo(TEST_EMAIL_ADDRESS);
    }

    @Test
    void createAccount_fails() {
        when(mockAccount.getEmailAddress()).thenReturn("afc78cbewa87rh3iu4bewfuyg2huew");

        assertThrows(IllegalArgumentException.class, () -> accountsService.createAccount(mockAccount));
    }

    @Test
    void findAccount() {
        Optional<UserAccount> optionalMockAccount = Optional.of(mockAccount);
        when(userAccountRepository.findById(Mockito.anyInt())).thenReturn(optionalMockAccount);

        Optional<UserAccount> account = accountsService.findAccount(1);
        assertThat(account.isPresent()).isTrue();
        assertThat(account.get().getEmailAddress()).isEqualTo(TEST_EMAIL_ADDRESS);
    }
}