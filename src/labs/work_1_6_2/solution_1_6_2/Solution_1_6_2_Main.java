package labs.work_1_6_2.solution_1_6_2;

/*----- 1.6.3. ----*/

/* Create a class Main with a main() method, which creates
   an instance of Action and multiple users with different
   access rights. Then demonstrates working with the file
   for different users */

public class Solution_1_6_2_Main {
    public static void main(String[] args) {
        User[] users = createUsers();
        Action action = new Action();

        action.write(users[0], "write data to a file");
        action.write(users[1], "write data to a file");

        String result = action.read(users[0]);
        result = action.read(users[1]);

        System.out.println("File contents:" + result);
    }

    static User[] createUsers() {
        User[] users = new User[2];
        User user;

        user = new User("Peter");
        user.addPermissions(PermissionAction.USER_READ);
        users[0] = user;

        user = new User("Alex");
        user.addPermissions(PermissionAction.USER_CHANGE);
        user.addPermissions(PermissionAction.USER_READ);
        users[1] = user;

        return users;
    }
}
