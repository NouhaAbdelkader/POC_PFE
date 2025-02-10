package LambdaExpression;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class LambdaExpression {
    Predicate<String> predicate =
            (String s) -> {
                return s.length() == 3;
            };


//********** simplifier le comportement ===> :
    Predicate<String> p = s -> s.length() == 3;



    //Let us write a lambda that consumes a String and prints on System.out. The syntax can be this one:
    Consumer<String> print = s -> System.out.println(s);

// So let us write a runnable that tells us that it is running:
Runnable runnable = () -> System.out.println("I am running");





// let's write a lambda expression in a method :
List<String> retainStringsOfLength3(List<String> strings) {

    Predicate<String> predicate = s -> s.length() == 3;
    List<String> stringsOfLength3 = new ArrayList<>();
    for (String s: strings) {
        if (predicate.test(s)) {
            stringsOfLength3.add(s);
        }
    }
    return stringsOfLength3;
}

    // ***************************************************************************************


    Comparator<Integer> comparator = (i1, i2) -> Integer.compare(i1, i2);

 // ********** this can be writeen like :
 Comparator<Integer> comparator1 = Integer::compare;

}
