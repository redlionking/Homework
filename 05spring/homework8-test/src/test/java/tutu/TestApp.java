package tutu;

import homework8.base.School;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class TestApp {

    @Resource
    private School school;

    @Test
    public void testSchool(){
        System.out.println(school);
    }
}
