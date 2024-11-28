package ee.mihkel.veebipood.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.modelmapper.ModelMapper;

import java.util.Random;

@Configuration
public class AppConfig {

    // @Autowired --> 1 mälukoht
    @Bean
    public Random getRandom() {
        return new Random();
    }

    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
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
