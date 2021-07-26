package homework2.javas;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Toothpick implements Small {

    @Value("1mm")
    private String length;


    public void prick() {
        System.out.println("我的很大，你忍一下");
    }
}
