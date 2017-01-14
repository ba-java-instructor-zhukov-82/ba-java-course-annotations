package presentation.predefined_annotations;

public class DeprecatedTest {

    public static void main(String[] args) {
        deprecatedTest(); //deprecated in compile time
    }

    /*** @deprecated explanation of why it was deprecated */
    @Deprecated
    private static void deprecatedTest() {
        System.out.println("\'@Deprecated\' annotation test!");
    }
}
