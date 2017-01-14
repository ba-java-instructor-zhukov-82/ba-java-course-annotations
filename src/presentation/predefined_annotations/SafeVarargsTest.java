package presentation.predefined_annotations;

import javax.lang.model.element.Element;

public class SafeVarargsTest {

    public static void main(String[] args) {
        safeVarargsTest("hello", true, 1, 26.5f);
        unsafeVarargsTest(false, "buy", 1, 26.5f);
    }

    /* @SafeVarargs annotation, when applied to a method
       or constructor, asserts that the code does not perform
       potentially unsafe operations on its varargs parameter.
       When this annotation type is used, unchecked warnings
       relating to varargs usage are suppressed */
    @SafeVarargs()
    private static void safeVarargsTest(Object... varargs) {
        for(Object element : varargs)
            System.out.println(element);
    }

    @SuppressWarnings("unsafe varargs")
    private static void unsafeVarargsTest(Object... varargs) {
        for(Object element : varargs) {
            boolean flag = (boolean) element;
            System.out.println(flag);
        }
    }
}
