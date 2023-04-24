package Task_3;

import java.util.ArrayList;

public class JournalTask {
    public static void main(String[] args) {
        int weeksNumber = 10;
        int groupsCount = 3;
        int studentsNumberInGroup = 30;

        var groups = Generator.GenerateGroups(groupsCount);

        var students = Generator.GenerateStudents(groups, studentsNumberInGroup);

        var journal = new Journal(students);

        var teacher = new TeacherThread(journal, weeksNumber);
        var assistants = new ArrayList<AssistantThread>();

        for (var group: groups)
        {
            var assistant = new AssistantThread(journal, group, weeksNumber);
            assistants.add(assistant);
        }

        teacher.start();

        for (var assistant: assistants)
        {
            assistant.start();
        }

        try {
            teacher.join();

            for (var assistant: assistants)
            {
                assistant.join();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Grades count: " + journal.getGradesCount());
        System.out.println("Journal: " + journal.getGradesMap());
    }
}
