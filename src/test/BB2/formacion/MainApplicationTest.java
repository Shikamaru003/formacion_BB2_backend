package BB2.formacion;

import BB2.formacion.models.Role;
import BB2.formacion.models.User;
import BB2.formacion.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MainApplicationTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void whenFindByUserName_thenReturnUser() {
        User user = new User("username_test", "password_test", Role.ROLE_ADMIN);
        userRepository.save(user);
        User found = userRepository.findByUsername(user.getUsername()).get();
        assertThat(found.getUsername()).isEqualTo(user.getUsername());
    }

}
