package presentation.custom_annotations;

import java.lang.reflect.Method;

public class CustomAnnotationTest {

    public static void main(String[] args) {
        customAnnotationTest();
    }

    private static void customAnnotationTest() {
        Class<?> cls = AnnotatedClass.class;
        Method[] methods = cls.getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(CustomAnnotation.class)) {
                CustomAnnotation ma = method.getAnnotation(CustomAnnotation.class);
                System.out.println(method.getName() +
                                   "->" + ma.param1() + ", " + ma.param2());

            }
        }
    }
}