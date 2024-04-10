package  com.appsdeveloperblog.app.ws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.context.annotation.Bean;
@SpringBootApplication
public class MobileAppWsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MobileAppWsApplication.class, args);
	}
	

    @Bean
    BCryptPasswordEncoder bCryptPasswordEncode(){
		
		return new BCryptPasswordEncoder();
		
	}


}
