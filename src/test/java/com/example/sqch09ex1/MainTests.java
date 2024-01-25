package com.example.sqch09ex1;

import com.example.sqch09ex1.controllers.LoginController;
import com.example.sqch09ex1.controllers.MainController;
import com.example.sqch09ex1.model.LoginProcessor;
import com.example.sqch09ex1.services.LoggedUserManagementService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MainTests {

	@Mock
	private Model model;

	@Mock
	private LoginProcessor loginProcessor;

	@Mock
	private LoggedUserManagementService loggedUserManagementService;

	@InjectMocks
	private LoginController loginController;

	@InjectMocks
	private MainController mainController;

	@Test
	public void loginPostLoginSucceedsTest() {
		given(loginProcessor.login()).willReturn(true);

		String result = loginController.loginPost("username", "password", model);

		assertEquals("redirect:/main", result);
	}

	@Test
	public void loginPostLoginFailsTest() {
		given(loginProcessor.login()).willReturn(false);

		String result = loginController.loginPost("username", "password", model);

		assertEquals("login.html", result);

		verify(model).addAttribute("message", "Login failed!");
	}

	@Test
	public void mainControllerLoginSucceedsTest() {
		given(loggedUserManagementService.getUsername()).willReturn("artem");

		String result = mainController.home(null, model);

		assertEquals("main.html", result);
		verify(model).addAttribute("username", "artem");
	}

	@Test
	public void mainControllerLogoutTest() {
		String result = mainController.home("Logout", model);

		assertEquals("redirect:/", result);
		verify(loggedUserManagementService, atLeastOnce()).setUsername(null);
	}

}
