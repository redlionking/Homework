package homework8.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Klass {
    private String name;
    private int id;
    private List<Student> students = new ArrayList<>();

    public Klass(String name,int id){
        this.name = name;
        this.id = id;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public List<Student> getStudents(){
        return students;
    }
}
