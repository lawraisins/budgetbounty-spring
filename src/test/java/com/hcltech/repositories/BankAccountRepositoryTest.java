package com.hcltech.repositories;

import com.hcltech.models.BankAccount;
import com.hcltech.models.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.boot.test.autoconfigure.jdbc.TestDatabaseAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // Use actual DB settings
public class BankAccountRepositoryTest {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testFindByUserUserId() {
        // Create test user
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        entityManager.persist(user);

        // Create bank account for user
        BankAccount account = new BankAccount();
        account.setUser(user);
        account.setBankAccountNumber("1234567890");
        entityManager.persist(account);

        // Flush changes
        entityManager.flush();

        // Test repository method
        List<BankAccount> accounts = bankAccountRepository.findByUserUserId(user.getUserId());

        assertNotNull(accounts);
        assertEquals(1, accounts.size());
        assertEquals("1234567890", accounts.get(0).getBankAccountNumber());
    }
}
