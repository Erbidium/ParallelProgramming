package Task_3;

public class JournalTask {
    public static void main(String[] args) {
        int weeksNumber = 10;
        int groupsCount = 3;
        int studentsNumberInGroup = 30;

        var groups = Generator.GenerateGroups(3);

        var Journal = new Journal();

        var teacherThread = new TeacherThread()
    }
}
