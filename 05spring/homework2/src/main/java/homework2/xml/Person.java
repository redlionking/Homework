package homework2.xml;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person implements Rap{

    private String name;

    private Toothpick toothpick;


    public void rape() {
        toothpick.prick();
    }
}
