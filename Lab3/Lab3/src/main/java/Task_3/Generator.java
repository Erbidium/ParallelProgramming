package Task_3;

import java.util.ArrayList;

public class Generator {
    public static ArrayList<Group> GenerateGroups(int groupsNumber)
    {
        var groups = new ArrayList<Group>();

        var groupName = "group";

        for (int i = 0; i < groupsNumber; i++)
        {
            var group = new Group(groupName + (i + 1));
            groups.add(group);
        }

        return groups;
    }

    public static ArrayList<Student> GenerateStudents(ArrayList<Group> groups, int studentsNumberInGroup)
    {
        var students = new ArrayList<Student>();

        for (var group: groups) {

            var studentName = "name";
            var studentSurname = "surname";

            for (int i = 0; i < studentsNumberInGroup; i++)
            {
                var student = new Student
                (
                    studentName + (i + 1),
                    studentSurname + (i + 1),
                    group
                );

                students.add(student);
            }
        }

        return students;
    }
}
