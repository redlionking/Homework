package homework8.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "school")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchoolProperties {
    private String schoolName;

    private List<Integer> klassIds;
    private List<String> klassNames;

    private List<Integer> studentIds;
    private List<String> studentNames;

    private List<Map<String, Integer>> studentOfKlass;


}
