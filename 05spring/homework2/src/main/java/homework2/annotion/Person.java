package homework2.annotion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component()
public class Person implements Rap {

    @Value("wyf")
    private String name;

    @Autowired
    private Toothpick toothpick;


    public void rape() {
        toothpick.prick();
    }
}
