package com.example.RollToo;

import com.example.RollToo.enitities.User;
import com.example.RollToo.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {

        @Autowired
        private UserRepository repo;

        @Autowired
        private TestEntityManager entityManager;


        //Below:Add new user to database unit test
        @Test
        public void testCreateUser(){
            User user = new User();

            user.setEmail("testUser2@gmail.com");
            user.setPassword("test2");
            user.setUsername("test2");
            user.setEnabled(true);

            User savedUser = repo.save(user);

            User existUser = entityManager.find(User.class, savedUser.getId());

            assertThat(existUser.getEmail()).isEqualTo(user.getEmail());
        }

        //Below: unit test to find user with email present in database .
        @Test
        public void testFindByEmail(){
            String email = "testUser@gmail.com";

            User user = repo.findByEmail(email);

            assertThat(user).isNotNull();
        }

        //Below: unit test to find user with email not present in database.
        @Test
        public void testFindByEmailFail(){
            String email = "testUserNotPresent@gmail.com";

            User user = repo.findByEmail(email);

            assertThat(user).isNotNull();
        }



        @Test
        public void testListAll(){
            Iterable<User> users = repo.findAll();
//        Assertions.assertTrue(users).hasGreaterThan(0);
            for (User user: users){
                System.out.println(user);
            }
        }

        @Test
        public void testUpdateUser() {
            int userId = 1;
            Optional<User> optionalUser = repo.findById(userId);
            User user= optionalUser.get();
            user.setUsername("testUser1.2");
            repo.save(user);

            User updatedUser = repo.findById(userId).get();
            Assertions.assertSame(updatedUser.getUsername(),"testUser1.2");

        }

        @Test
        public void testGet(){
            int userId = 2;
            Optional<User> optionalUser = repo.findById(userId);
            Assertions.assertTrue(optionalUser.isPresent());
            System.out.println(optionalUser.get());

        }


        @Test
        public void testDeleteUser(){
            int userId = 1;
            repo.deleteById(userId);
            Optional<User> optionalUser = repo.findById(userId);
            Assertions.assertFalse(optionalUser.isPresent());
        }


    }
