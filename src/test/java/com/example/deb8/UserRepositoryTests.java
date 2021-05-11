package com.example.deb8;

import com.example.deb8.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTests  {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCreateUser() {
         User user = new User();
         user.setEmail("d.wall@gmail.com");
         user.setPassword("12345");
         user.setFirstName("Daniel");
         user.setLastName("Wall");

         User savedUser = userRepository.save(user);

         User existUser = entityManager.find(User.class, savedUser.getId());

    }
}
