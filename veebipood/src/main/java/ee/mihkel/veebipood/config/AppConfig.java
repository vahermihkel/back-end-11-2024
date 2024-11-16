package ee.mihkel.veebipood.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.Random;
import java.util.Scanner;

@Configuration
public class AppConfig {

    @Bean
    public Random getRandom() {
        return new Random();
    }

//    @Bean
//    public Scanner getScanner() {
//        return new Scanner(System.in);
//    }

//    @Bean
//    public RestTemplate getRestTemplate() {
//        return new RestTemplate();
//    }


}
