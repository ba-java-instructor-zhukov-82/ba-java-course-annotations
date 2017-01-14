package labs.work_1_6_2_and_1_6_3.solution_1_6_2_and_1_6_3;


import java.io.*;
import java.lang.reflect.Method;


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
