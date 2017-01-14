package presentation.custom_annotations;

public class AnnotatedClass {

    @CustomAnnotation(param1 = "a1", param2 = "a2")
    public void doJob1() {}

    @CustomAnnotation(param2 = "b2")
    public void doJob2() {}

}
