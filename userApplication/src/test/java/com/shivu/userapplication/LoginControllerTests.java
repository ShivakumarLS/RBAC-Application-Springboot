package com.shivu.userapplication;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;

import com.shivu.userapplication.controller.LoginController;
import com.shivu.userapplication.model.ApplicationUser;
import com.shivu.userapplication.model.LoginDTO;
import com.shivu.userapplication.model.LoginResponseDTO;
import com.shivu.userapplication.repository.UserRepository;
import com.shivu.userapplication.service.AuthenticationService;

import jakarta.servlet.http.HttpServletResponse;

@SpringBootTest
public class LoginControllerTests {

    @Mock
    private AuthenticationService authenticationService;
    @Mock
    private HttpServletResponse response;

    @InjectMocks
    LoginController loginController;

    @Mock
    UserRepository userRepository;

    @BeforeEach
    @SuppressWarnings("deprecation")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testShowLoginForm() {
        Model model = mock(Model.class);
        String result = loginController.showLoginForm(model);
        assertEquals("login", result);
        verify(model).addAttribute(eq("LoginForm"), any(LoginDTO.class));
    }

    @Test
    public void testLoginUser() throws Exception {
        Model model = mock(Model.class);
        LoginDTO user = new LoginDTO("username", "password");
        ApplicationUser mockUser = mock(ApplicationUser.class);
        LoginResponseDTO loginResponseDTO = new LoginResponseDTO(mockUser, "jwt_token");
        when(authenticationService.loginUser("username", "password")).thenReturn(loginResponseDTO);

        String result = loginController.LoginUser(user, model, response);

        assertEquals("welcome", result);
        verify(response).addHeader("token", "jwt_token");
        verify(model).addAttribute("UserName", "username");
        verify(model).addAttribute("token", "jwt_token");
    }

    @Test
    public void testLoginUser_Failure() throws Exception {
        Model model = mock(Model.class);
        LoginDTO user = new LoginDTO("username", "password");
        ApplicationUser mockUser = new ApplicationUser();

        LoginResponseDTO loginResponseDTO = mock(LoginResponseDTO.class);

        when(authenticationService.loginUser("usrname", "pasword")).thenReturn(loginResponseDTO);
        when(loginResponseDTO.getJwt()).thenReturn("notAToken");
        when(loginResponseDTO.getUser()).thenReturn(mockUser);
        assertThrows(Exception.class,
         () -> {
            loginController.LoginUser(user, model, response);
                });
    }
}
