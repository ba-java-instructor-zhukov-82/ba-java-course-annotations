package labs.work_1_6_2_and_1_6_3.solution_1_6_2_and_1_6_3;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*----- 1.6.2 ----*/

/* Describe custom annotation MyPermission with value() method, which returns
   value PermissionAction of type. Annotation should have a policy of
   retention RetentionPolicy.RUNTIME and used with methods */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Permission {
    PermissionAction value();
}
