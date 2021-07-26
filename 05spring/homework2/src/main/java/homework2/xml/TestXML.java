package homework2.xml;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestXML {

    @Test
    public void test(){
        ApplicationContext context = new ClassPathXmlApplicationContext("xml/wyf.xml");
        Person wyf = context.getBean("wyf",Person.class);
        wyf.rape();
    }

}
