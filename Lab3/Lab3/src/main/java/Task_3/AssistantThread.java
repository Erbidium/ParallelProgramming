package Task_3;

import java.util.Random;

public class AssistantThread extends Thread {
    private Journal journal;

    private Group group;

    private int weeksNumber;

    public AssistantThread(Journal journal, Group group, int weeksNumber)
    {
        this.journal = journal;
        this.group = group;
        this.weeksNumber = weeksNumber;
    }

    @Override
    public void run()
    {
        for (int i = 0; i < weeksNumber; i++)
        {
            var random = new Random();

            var students = journal.getStudentsOfGroup(group);
            for (var student: students) {
                journal.AddStudentGrade(student, random.nextInt(100));
            }
        }
    }
}
