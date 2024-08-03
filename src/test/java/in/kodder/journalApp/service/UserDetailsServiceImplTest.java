package in.kodder.journalApp.service;

import in.kodder.journalApp.entity.UserEntity;
import in.kodder.journalApp.repository.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;

import static org.mockito.Mockito.when;

@SpringBootTest
public class UserDetailsServiceImplTest {

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @MockBean
    private UserRepository userRepository;

    @Test
    void loadUserByUsernameTest() {
//        when(userRepository.findByUsername())
        UserDetails user = userDetailsService.loadUserByUsername("ram");
    }
}
