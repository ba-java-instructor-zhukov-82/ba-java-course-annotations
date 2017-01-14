##Laboratory Work 1.6.2 - 1.6.3

<p>
    <span>
        Create new project called TestAnnotation2. 
        Add package “com.brainacad.oop.test custom annotation”.
        Write a program that implements a different access to the file 
        operation for different users using annotations.
    </span>
</p> 

<ol>
    <li>
        Create a enum type PermissionAction which specify access to 
        operations on the file (example, USER_READ, USER_CHANGE, …). 
    </li>
    <li>
        Create a class User with private fields: name of String type and
        permissions of List<PermissionAction> type. Add to User 
        constructor with name argument and override toString() method to
        return name of user. Add to User methods to get the permissions 
        list and add a new permission. 
    </li>
    <li>
        Describe custom annotation MyPermission with value() method, 
        which returns value PermissionAction of type. Annotation should 
        have a policy of retention RetentionPolicy.RUNTIME and used with
        methods. 
    </li>
    <li>
        Create an Action class with methods for reading and writing to a
        file. Methods should be annotated @MyPermission with proper 
        access rights. Methods must to get a user who wants execute 
        them. Methods of using reflection make a decision: to allow 
        access to the file and perform the work, or refuse access and to
        give a message. 
    </li>
    <li>
        Create a class Main with a main() method, which creates an 
        instance of Action and multiple users with different access 
        rights. Then demonstrates working with the file for different 
        users.
    </li>
</ol>

####Here is the solution code:

*Action.java*   
```java
/*----- 1.6.3. -----*/

/* Create an Action class with methods for reading and writing to a file.
   Methods should be annotated @MyPermission with proper access rights.
   Methods must to get a user who wants execute them. Methods of using
   reflection make a decision: to allow access to the file and perform
   the work, or refuse access and to give a message */

public class Action {
        private File file;

    public Action() {
        this.file = new File("d:\\Temp\\data.txt");
    }

    @Permission(PermissionAction.USER_READ)
    public String read(User user) {
        String text = null;
        try {
            Class[] params = new Class[] {User.class};
            Method mm = this.getClass().getMethod("read", params);
            Permission permissionAction = mm.getAnnotation(Permission.class);

            if ((user != null)  && (user.getPermissions().contains(permissionAction.value()))) {
                text = this.readFile();
                System.out.println(user + ", Read is successful");
            } else {
                System.out.println(user + ", Operation of read is not permitted!");
            }
        }catch (NoSuchMethodException exp) {
            exp.printStackTrace();
        }
        return text;
    }

    private String readFile() {
        String text = "";
        String line = null;
        try (BufferedReader br = new BufferedReader(new FileReader(this.file))) {
            while ((line = br.readLine()) != null) {
                text = text + line + "\n";
            }
        } catch (IOException ee) {
            ee.printStackTrace();
        }
        return text;
    }

    @Permission(PermissionAction.USER_CHANGE)
    public void write(User user, String str) {
        try {
            Class[] params = new Class[] {User.class, String.class};
            Method mm = this.getClass().getMethod("write", params);
            Permission permissionAction = mm.getAnnotation(Permission.class);

            if ((user != null)  && (user.getPermissions().contains(permissionAction.value()))) {
                this.writeInFile(str);
                System.out.println(user + ", Write is successful");
            } else {
                System.out.println(user + ", Operation of write is not permitted!");
            }
        } catch (NoSuchMethodException exp) {
            exp.printStackTrace();
        }
    }

    private void writeInFile(String str) {
        try (RandomAccessFile rf = new RandomAccessFile(this.file, "rw")) {
            long length = rf.length();
            rf.seek(length);
            rf.writeBytes("\r\n");
            rf.writeBytes(str);
        } catch (IOException ee) {
            ee.printStackTrace();
        }
    }
}
```

*Permission.java*   
```java
/*----- 1.6.2 ----*/

/* Describe custom annotation MyPermission with value() method, which returns
   value PermissionAction of type. Annotation should have a policy of
   retention RetentionPolicy.RUNTIME and used with methods */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Permission {
    PermissionAction value();
}
```

*PermissionAction.java*   
```java
/*----- 1.6.2 ----*/

/* Create a enum type PermissionAction which specify access to operations
   on the file (example, USER_READ, USER_CHANGE, …) */

public enum PermissionAction {
    USER_READ,
    USER_CHANGE
}
```

*User.java*   
```java
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
```

*Solution_1_6_2_and_1_6_3_Main.java*   
```java
/*----- 1.6.3. ----*/

/* Create a class Main with a main() method, which creates
   an instance of Action and multiple users with different
   access rights. Then demonstrates working with the file
   for different users */

public class Solution_1_6_2_and_1_6_3_Main {
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
```