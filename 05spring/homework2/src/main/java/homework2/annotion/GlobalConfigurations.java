package homework2.annotion;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


@Configuration
@Import({ToothpickConfigurations.class,PersonConfigurations.class})
public class GlobalConfigurations {

}
