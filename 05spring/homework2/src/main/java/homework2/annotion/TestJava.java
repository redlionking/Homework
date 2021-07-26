package homework2.annotion;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class TestJava {


    @Test
    public void test(){
        ApplicationContext context = new AnnotationConfigApplicationContext(GlobalConfigurations.class);
        Person wyf = context.getBean("wyf",Person.class);
        wyf.rape();

    }

}
