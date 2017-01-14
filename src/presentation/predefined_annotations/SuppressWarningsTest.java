package presentation.predefined_annotations;

import static java.util.Date.*;

public class SuppressWarningsTest {

    public static void main(String[] args) {
        suppressWarningsTest();
    }

    /* @SuppressWarnings annotation tells the compiler to
       suppress specific warnings that it would otherwise
       generate */
    @SuppressWarnings("deprecation")
    public static void suppressWarningsTest() {
        long dt = parse("Mon Feb 1 11:45:00 EDT 1982");
    }
}
