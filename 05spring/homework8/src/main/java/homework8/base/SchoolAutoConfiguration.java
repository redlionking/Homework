package homework8.base;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Configuration
@ConditionalOnClass(School.class)
@EnableConfigurationProperties(SchoolProperties.class)
@PropertySource("classpath:application.properties")
@ConditionalOnProperty(prefix = "school", value = "enabled", havingValue = "true")
public class SchoolAutoConfiguration {

    @Autowired
    private SchoolProperties schoolProperties;

    @Bean
    public School school() {
        List<Integer> studentIds = schoolProperties.getStudentIds();
        List<String> studentNames = schoolProperties.getStudentNames();
        List<Integer> klassIds = schoolProperties.getKlassIds();
        List<String> klassNames = schoolProperties.getKlassNames();
        List<Map<String, Integer>> studentOfKlass = schoolProperties.getStudentOfKlass();


        List<Student> students = new ArrayList<>(studentIds.size());
        for (int i=0; i<studentIds.size(); i++) {
            students.add(new Student(studentIds.get(i), studentNames.get(i)));
        }




        List<Klass> klasses = new ArrayList<>();

        for (int i=0; i<klassIds.size(); i++) {
            klasses.add(new Klass(klassNames.get(i),klassIds.get(i)));
        }

        for (Map<String,Integer> info: studentOfKlass) {
            Integer klassId = info.get("klassId");
            Student student = students.get( info.get("studentId"));
            (klasses.get(klassId)).addStudent(student);
        }

        //System.out.println(studentIds.toString());
        //System.out.println(studentNames.toString());
        //System.out.println(klassIds.toString());
        //System.out.println(klassNames.toString());
        //System.out.println(studentOfKlass.toString());

        return new School(schoolProperties.getSchoolName(),klasses);
    }
}
