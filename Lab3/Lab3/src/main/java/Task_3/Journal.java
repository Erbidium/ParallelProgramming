package Task_3;

import java.util.ArrayList;
import java.util.Map;

public class Journal {
    private Map<Group, Map<Student, ArrayList<Integer>>> gradesMap;

    private ArrayList<Student> students;

    public Journal(ArrayList<Student> students)
    {
        this.students = students;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }
}
