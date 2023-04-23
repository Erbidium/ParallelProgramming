package Task_3;

import java.util.Random;

public class AssistantThread extends Thread {
    private Journal journal;

    private Group group;

    public AssistantThread(Journal journal, Group group)
    {
        this.journal = journal;
        this.group = group;
    }

    @Override
    public void run()
    {
        while (true)
        {
            var random = new Random();

            var students = journal.getStudentsOfGroup(group);
            for (var student: students) {
                journal.AddStudentGrade(student, random.nextInt(100));
            }
        }
    }
}
