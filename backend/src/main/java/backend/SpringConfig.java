package backend;

import backend.domain.User;
import backend.repository.JPAUserRepository;
import backend.repository.UserRepository;
import backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.swing.text.html.parser.Entity;

@Configuration
public class SpringConfig {

    private EntityManager entityManager;

    @Autowired
    public SpringConfig(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Bean
    public UserService userService() {
        return new UserService(userRepository());
    }
    @Bean
    public UserRepository userRepository() {
        return new JPAUserRepository(entityManager);
    }
}
