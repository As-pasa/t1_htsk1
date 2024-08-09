package com.as_pasa.t1_htsk1.aspects;

import com.as_pasa.t1_htsk1.exceptions.ApplicationException;
import com.as_pasa.t1_htsk1.models.User;
import com.as_pasa.t1_htsk1.repositories.UserRepository;
import com.as_pasa.t1_htsk1.services.UserService;
import com.as_pasa.t1_htsk1.utils.ContainsSubstringsMatcher;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class LoggingAspectTest {
    @MockBean
    private Logger logger;

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;


    private User user = new User("Dodzee", "mail@mail.com", new ArrayList<>());
    private final Long userId = -1L;
    private final Long notExistingId = -2L;

    @BeforeEach
    public void setUp() {
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
    }

    @Test
    public void logIsTriggered() {
        userService.findById(userId);
        verify(logger, atLeastOnce()).info(contains("METHOD"));
    }

    @Test
    public void logIsCatchingMethodName() {
        userService.findById(userId);
        verify(logger, atLeastOnce()).info(contains("findById"));
    }

    @Test
    public void logIsCatchingArgs() {
        userService.findById(userId);
        verify(logger, atLeastOnce()).info(contains(userId.toString()));
    }

    @Test
    public void logIsCatchingSuccess() {
        userService.findById(userId);
        List<String> l = List.of(user.toString(), "SUCCESS", "METHOD");
        verify(logger, atLeastOnce()).info(argThat(new ContainsSubstringsMatcher(l)));
    }

    @Test
    public void logIsCatchingError() {
        try {
            userService.findById(notExistingId);
        } catch (ApplicationException ignored) {

        } finally {
            verify(logger, atLeastOnce()).error(contains("User Not Found"));

        }
    }

    @Test
    public void IgnoreMarkedMethodsNotLogged() {
        userService.initUser("Dodzee", "No@mail.com");
        verify(logger, times(0)).info(contains("METHOD"));
    }


}
