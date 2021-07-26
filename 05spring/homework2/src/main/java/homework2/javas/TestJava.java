package homework2.javas;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = GlobalConfigurations.class)
public class TestJava {


    @Autowired
    private Person wyf;

    @Test
    public void test2(){
        wyf.rape();
    }

    @Test
    public void test3(){
        ApplicationContext context = new AnnotationConfigApplicationContext("homework2.javas");//扫描包，类都行
        Person wyf1 = context.getBean("wyf",Person.class);
        wyf1.rape();
    }
}
