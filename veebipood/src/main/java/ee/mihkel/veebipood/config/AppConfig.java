package ee.mihkel.veebipood.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.modelmapper.ModelMapper;
import org.springframework.web.client.RestTemplate;

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

    @Bean
    public RestTemplate getRestTemplate(RestTemplateBuilder builder) {
        // vajadusel saan siis mingeid asju builderile juurde panna
        return builder.build(); // täpselt sama mis new RestTemplate();
    }


}
