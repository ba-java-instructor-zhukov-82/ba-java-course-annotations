package labs.work_1_6_2.solution_1_6_2;

import java.util.ArrayList;
import java.util.List;

/*----- 1.6.2 ----*/

/* Create a class User with private fields: name of String type and
   permissions of List type. Add to User constructor with name argument
   and override toString() method to return name of user. Add to User methods
   to get the permissions list and add a new permission */

public class User {
    private String name;
    private List<PermissionAction> permissions = new ArrayList<>();

    public User(String name) {
        this.name = name;
    }

    public void addPermissions(PermissionAction permission) {
        this.permissions.add(permission);
    }

    public List<PermissionAction> getPermissions() {
        return permissions;
    }

    @Override
    public String toString() {
        return "User: " + name;
    }
}
