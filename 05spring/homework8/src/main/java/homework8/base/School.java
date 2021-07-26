package homework8.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class School implements ISchool{
    private String name;
    private List<Klass> klass;

    public School(List<Klass> klass) {
        this.klass = klass;
    }

}
