package homework2.annotion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersonConfigurations {
    @Bean(name = "wyf")
    public Person person(){
        return new Person();
    }
}
