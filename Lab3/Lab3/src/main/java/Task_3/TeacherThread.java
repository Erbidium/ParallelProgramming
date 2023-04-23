package Task_3;

import java.util.Random;

public class TeacherThread extends Thread {
    private Journal journal;

    private int weeksNumber;

    public TeacherThread(Journal journal, int weeksNumber)
    {
        this.journal = journal;
        this.weeksNumber = weeksNumber;
    }

    @Override
    public void run()
    {
        for (int i = 0; i < weeksNumber; i++)
        {
            var random = new Random();

            var students = journal.getStudents();
            for (var student: students) {
                journal.AddStudentGrade(student, random.nextInt(100));
            }
        }
    }
}
