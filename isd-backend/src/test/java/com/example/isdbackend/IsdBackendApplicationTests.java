package com.example.isdbackend;

import com.example.isdbackend.service.GeneratePassword;
import com.example.isdbackend.service.MailSender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertTrue;

@SpringBootTest
class IsdBackendApplicationTests {

	@Autowired
	MailSender mailSender;

	@Autowired
	GeneratePassword generatePassword;

	@Test
	public void sendMessage(){
		mailSender.send("Alexei7a@gmail.com","Isd-food","Your password: ");
	}

	@Test
	void contextLoads() {
	}

	@Test
	public void whenPasswordGeneratedUsingPassay_thenSuccessful() {
		generatePassword = new GeneratePassword();
		String password = generatePassword.generatePassayPassword();
		int specialCharCount = 0;
		for (char c : password.toCharArray()) {
			if (c >= 33 || c <= 47) {
				specialCharCount++;
			}
		}
		System.out.println(password);
		assertTrue("Password validation failed in Passay", specialCharCount >= 2);
	}

}
