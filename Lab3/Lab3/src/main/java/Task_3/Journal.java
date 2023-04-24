package Task_3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class Journal {
    private Map<Group, Map<Student, ArrayList<Integer>>> gradesMap;

    private AtomicInteger gradesCount;

    private ArrayList<Student> students;

    public Journal(ArrayList<Student> students)
    {
        this.students = students;

        InitJournal(students);
    }

    private void InitJournal(ArrayList<Student> students)
    {
        gradesCount = new AtomicInteger();
        gradesCount.set(0);

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
        return gradesCount.get();
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
            gradesCount.incrementAndGet();
            studentGrades.add(grade);
        }
    }
}
