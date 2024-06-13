package org.example.supportservice.user.repository;

import org.example.supportservice.user.entity.Role;
import org.example.supportservice.user.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void itShouldFindByUsername() {
        UserEntity user = new UserEntity();
        user.setUsername("username");
        user.setPassword("password");
        user.setRole(Role.User);

        userRepository.save(user);

        boolean found = userRepository.findByUsername("username").isPresent();

        assertThat(found).isTrue();
    }
}