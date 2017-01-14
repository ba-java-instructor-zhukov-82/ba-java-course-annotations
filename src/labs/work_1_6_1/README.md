##Laboratory Work 1.6.1

<p><span>
    Create new project called TestAnnotation1. 
    Add package “com.brainacad.oop.testpredefannotation”.
</span></p> 

<ol>
    <li>
        Create a class Main with a findMax() static method with array varargs
        type of int, which returns the maximum of the parameters passed.
        Add in class Main the main() method, which invokes a findMax() method
        and output result on console. Execute the program. 
    </li>
    <li>
        Add into the description a findMax() method @Deprecated annotation 
        and and look what happened. Add into the description a main() method 
        @SuppressWarnings("deprecation") annotation and look what happened. 
    </li>
    <li>
        Add in class Main the generic static method findMax() with array 
        parameter varargs type of T, which returns the maximum of the 
        parameters passed. Add in main() method invocation a findMax() 
        generic method and output result on console. Consider that tells the 
        compiler. 
    </li>
    <li>
        Add into the description a findMax() generic method @SafeVarargs 
        annotation and look what happened.
   </li>
</ol>

####Here is the solution code:

*solution_1_6_1.java*   
```java
public class solution_1_6_1 {

    /* Add into the description a findMax() method @Deprecated annotation
       and and look what happened. Add into the description a main() method
       @SuppressWarnings("deprecation") annotation and look what happened */
    @SuppressWarnings("deprecation")
    public static void main(String[] args) {
        int[] arrayInt = {5,2,7,9,3,8,5,4};
        System.out.println("Only integers: max = " + findMax(arrayInt));

        System.out.println("For any data type, int: max = " +
                           findMax(new Integer[] {-6, -3, -1, -9, -5, -2}));
        System.out.println("For any data type, long: max = " +
                           findMax(87L, 45L, 111L, 2L, 88L));
        System.out.println("For any data type, double: max = " +
                           findMax(.7,2.7,9.1,0.009,-4.5,22.23, -45.9));
    }

    /* Create a class Main with a findMax() static method with array varargs
       type of int, which returns the maximum of the parameters passed.
       Add in class Main the main() method, which invokes a findMax() method
       and output result on console. Execute the program. */

    /**
     * @deprecated use of findMax(int...)
     * is discouraged, use
     * findMax(T...) instead.
     */
    @Deprecated
    public static int findMax(int... numbers) {
        int max = numbers[0];
        for (int element : numbers)
            max = element > max ? element : max;
        return max;
    }


    /* Add in class Main the generic static method findMax() with array
       parameter varargs type of T, which returns the maximum of the
       parameters passed. Add in main() method invocation a findMax()
       generic method and output result on console. Consider that tells the
       compiler */

    /* Add into the description a findMax() generic method @SafeVarargs
       annotation and look what happened */
    @SafeVarargs
    public static <T extends Number> T findMax( T... numbers) {
        T max = numbers[0];
        for (T element : numbers)
            max = max.doubleValue() < element.doubleValue() ? element : max;
        return max;
    }
}
```
