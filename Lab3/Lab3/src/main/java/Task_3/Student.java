package Task_3;

public class Student {
    private String name;

    private String surname;

    private Group group;

    Student(String name, String surname, Group group)
    {
        this.name = name;
        this.surname = surname;
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Group getGroup() {
        return group;
    }
}
