package Task_3;

import java.util.ArrayList;

public class Generator {
    public ArrayList<Group> GenerateGroups(int groupsNumber)
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
}
