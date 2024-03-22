package com.shivu.userapplication;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.shivu.userapplication.controller.UserController;

public class UserControllerTests {

    @InjectMocks
    UserController userController;

    @SuppressWarnings("deprecation")
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testHelloUserController() throws Exception {
        String expected = "User Level Access";
        String actual = userController.helloUserController();
        assertEquals(expected, actual);
    }
}
