package Streams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class StreamDemo {

    public static void main(String[] args) {

        // **************** 1  ***********************
        List<String> strings = List.of("one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten");
        var map = strings.stream()
                .collect(groupingBy(String::length, counting()));
        map.forEach((key, value) -> System.out.println(key + " :: " + value));



        //***********************************

        List<String> s = List.of("one", "two", "three", "four");
        List<Integer> lengths = s.stream()
                .map(String::length)
                .collect(Collectors.toList());
        System.out.println("lengths = " + lengths);

        // ********************* count() *************


        List<String> s2 = List.of("one", "two", "three", "four");
        long count = s2.stream()
                .map(String::length)
                .filter(length -> length == 3)
                .count();
        System.out.println("count = " + count);

                 // ********** exemple ***************


     // on doit remplir la liste !!!
        List<State> states = new ArrayList<>();



//         // Calcul de la population totale
//        int totalPopulation = 0;
//        for (State state: states) {
//            for (City city: state.getCities()) {
//                totalPopulation += city.getPopulation();
//            }
//        }
//
//        System.out.println("Total population = " + totalPopulation);



//  ====== >  avec stram et flatMap()

        //int totalPopulation =
        //        states.stream()
        //              .flatMap(state -> state.getCities().stream())
        //              .mapToInt(City::getPopulation)
        //              .sum();
        //
        //System.out.println("Total population = " + totalPopulation);




        ///////// **********************

        Function<String, Stream<Integer>> flatParser = S -> {
            try {
                return Stream.of(Integer.parseInt(S));
            } catch (NumberFormatException e) {
            }
            return Stream.empty();
        };

        List<String> s3 = List.of("1", " ", "2", "3 ", "", "3");
        List<Integer> ints =
                s3.stream()
                        .flatMap(flatParser)
                        .collect(Collectors.toList());
        System.out.println("ints = " + ints);


        //////////////////

        System.out.println("*********** skip() **************" );

        List<Integer> ins = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);

        List<Integer> result =
                ins.stream()
                        .skip(2)
                        .limit(5)
                        .collect(Collectors.toList());

        System.out.println("result = " + result);

 //////////  concat()
        System.out.println("*********** concat() **************" );
        List<Integer> list0 = List.of(1, 2, 3);
        List<Integer> list1 = List.of(4, 5, 6);
        List<Integer> list2 = List.of(7, 8, 9);

// 1st pattern: concat
        List<Integer> concat =
                Stream.concat(list0.stream(), list1.stream())
                        .collect(Collectors.toList());

// 2nd pattern: flatMap
        List<Integer> flatMap =
                Stream.of(list0.stream(), list1.stream(), list2.stream())
                        .flatMap(Function.identity())
                        .collect(Collectors.toList());

        System.out.println("concat  = " + concat);
        System.out.println("flatMap = " + flatMap);


        ////    peek()

        List<String> list = List.of("one", "two", "three", "four");
        List<String> r =
                list.stream()
                        .peek(l -> System.out.println("Starting with = " + l))
                        .filter(l -> l.startsWith("t"))
                        .peek(l -> System.out.println("Filtered = " + l))
                        .map(String::toUpperCase)
                        .peek(l -> System.out.println("Mapped = " + l))
                        .collect(Collectors.toList());
        System.out.println("result = " + r);



        /// un exemple qui compte le nombre d'avertissements dans un fichier journal
        Path log = Path.of("/tmp/debug.log"); // adjust to fit your installation
        try (Stream<String> lines = Files.lines(log)) {

            long warnings =
                    lines.filter(line -> line.contains("WARNING"))
                            .count();
            System.out.println("Number of warnings = " + warnings);

        } catch (IOException e) {
            // do something with the exception
        }



        //// sum() *********************
        System.out.println("**************** sum() *********************" );

        List<Integer> nb = List.of(3, 6, 2, 1);
        BinaryOperator<Integer> sum = (a, b) -> a + b;

        int rs = nb.get(0);
        for (int index = 1; index < nb.size(); index++) {
            rs = sum.apply(rs, nb.get(index));
        }
        System.out.println("sum = " + rs);



        ////
        // ******************** Max() *********************
        System.out.println("**************** Max()*********************" );
        List<Integer> ls = List.of(3, 6, 2, 1);
        BinaryOperator<Integer> max = (a, b) -> a > b ? a: b;

        int rr = ls.get(0);
        for (int index = 1; index < ls.size(); index++) {
            rr = max.apply(rr, ls.get(index));
        }
        System.out.println("max = " + rr);





        // ******************** reduce() *********************
        System.out.println(" ******************** reduce() *********************\n" );

        Stream<String> sts = Stream.of("one", "two", "three", "four");

        BinaryOperator<Integer> combiner = (length1, length2) -> length1 + length2;
        BiFunction<Integer, String, Integer> accumulator =
                (partialReduction, element) -> partialReduction + element.length();

        int rst = sts.reduce(0, accumulator, combiner);
        System.out.println("sum = " + rst);







 // *************************Comptage des éléments traités par un flux***********************

        Collection<String> stss =
                List.of("one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten");

        long c =
                stss.stream()
                        .filter(A -> A.length() == 3)
                        .count();
        System.out.println("count = " + count);




////// ******* Consommer chaque élément un par un **************

        Stream<String> sas = Stream.of("one", "two", "three", "four");

        sas.filter(z -> z.length() == 3)
                .map(String::toUpperCase)
                .forEach(System.out::println);






        // nous avons deux modèles pour stocker les éléments d'un flux dans une liste :

        /// *****************  1 liste modifiable  *******************

        Stream<String> s1 = Stream.of("one", "two", "three", "four");

        List<String> r1 =
                s1.filter(S -> S.length() == 3)
                        .map(String::toUpperCase)
                        .collect(Collectors.toList());





        /// *****************  2 liste non modifiable *******************

        Stream<String> S2 = Stream.of("one", "two", "three", "four");

        List<String> r2 =
                S2.filter( S3 -> S3.length() == 3)
                        .map(String::toUpperCase)
                        .toList();






        ///////////////   allmatch() , anymatch() , nonematch()   //////////

        System.out.println("******  allmatch() , anymatch() , nonematch() *******");

        boolean noBlank  =
                strings.stream()
                        .allMatch(Predicate.not(String::isBlank));
        boolean oneGT3   =
                strings.stream()
                        .anyMatch(q -> q.length() == 3);
        boolean allLT10  =
                strings.stream()
                        .noneMatch(q -> q.length() > 10);

        System.out.println("noBlank = " + noBlank);
        System.out.println("oneGT3  = " + oneGT3);
        System.out.println("allLT10 = " + allLT10);





        ///////// ******  groupingBy()  dans cet exemple on groupe les mots par longueur  *******

        System.out.println(" *******  groupingBy() ******* " );


        Map<Integer, List<String>> map1 =
                strings.stream()
                        .collect(Collectors.groupingBy(String::length));

        map1.forEach((key, value) -> System.out.println(key + " :: " + value));






        ///////// ******  groupingBy()  dans cet exemple on groupe les nb de mts qui ont mm longueur   *******


        System.out.println(" *******  groupingBy() and counting() ******* " );

        Map<Integer, Long> map2 =
                strings.stream()
                        .collect(
                                Collectors.groupingBy(
                                        String::length,
                                        Collectors.counting()));

        map2.forEach((key, value) -> System.out.println(key + " :: " + value));

        /// Parallelizing Streams

        int parallelSum =
                IntStream.range(0, 10)
                      //  .limit(5)
                        .parallel()
                        .sum();
        System.out.println("valeur de inStream " + parallelSum);
    }
    }
