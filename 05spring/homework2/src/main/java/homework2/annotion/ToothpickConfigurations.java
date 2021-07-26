package homework2.annotion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ToothpickConfigurations {
    @Bean
    public Toothpick toothpick(){
        return new Toothpick();
    }
}
