package Task_3;

import java.util.Random;

public class TeacherThread extends Thread {
    private Journal journal;

    public TeacherThread(Journal journal)
    {
        this.journal = journal;
    }

    @Override
    public void run()
    {
        while (true)
        {
            var random = new Random();

            var students = journal.getStudents();
            for (var student: students) {
                journal.AddStudentGrade(student, random.nextInt(100));
            }
        }
    }
}
