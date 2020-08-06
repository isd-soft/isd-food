package com.example.isdbackend;

import com.example.isdbackend.service.MailSender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class IsdBackendApplicationTests {

	@Autowired
	MailSender mailSender;

	@Test
	public void sendMessage(){
		mailSender.send("nikita.dolinskiy25@gmail.com","test","Test Spring message!");
	}

	@Test
	void contextLoads() {
	}

}
