package Task_3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Journal {
    private Map<Group, Map<Student, ArrayList<Integer>>> gradesMap;

    private int gradesCount;

    private ArrayList<Student> students;

    public Journal(ArrayList<Student> students)
    {
        this.students = students;

        InitJournal(students);
    }

    private void InitJournal(ArrayList<Student> students)
    {
        gradesCount = 0;

        gradesMap = new HashMap<>();

        for (var student: students) {
            var studentGroup = student.getGroup();

            gradesMap.putIfAbsent(studentGroup, new HashMap<>());

            var groupMap = gradesMap.get(studentGroup);

            groupMap.putIfAbsent(student, new ArrayList<>());
        }
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public int getGradesCount() {
        return gradesCount;
    }

    public Map<Group, Map<Student, ArrayList<Integer>>> getGradesMap() {
        return gradesMap;
    }

    public Set<Student> getStudentsOfGroup(Group group) {
        return gradesMap.get(group).keySet();
    }

    public void AddStudentGrade(Student student, int grade)
    {
        var groupMap = gradesMap.get(student.getGroup());

        var studentGrades = groupMap.get(student);

        synchronized (studentGrades)
        {
            gradesCount++;
            studentGrades.add(grade);
        }
    }
}
