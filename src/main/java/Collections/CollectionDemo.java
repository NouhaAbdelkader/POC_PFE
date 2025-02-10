package Collections;

import org.apache.catalina.User;

import java.util.*;

public class CollectionDemo {

    public static void main(String[] args) {
        Collection<String> strings = new ArrayList<>();
        strings.add("one");
        strings.add("two");
        System.out.println("strings = " + strings);
        strings.remove("one");
        System.out.println("strings = " + strings);

        if (strings.contains("one")) {
            System.out.println("one is here");
        }
        if (!strings.contains("three")) {
            System.out.println("three is not here");
        }

        ///// ******** Méthodes qui gèrent d'autres collections *************

// 1 containsAll()
        System.out.println("************** 1 ****************** ");

        Collection<String> first = new ArrayList<>();
        first.add("one");
        first.add("two");

        Collection<String> second = new ArrayList<>();
        second.add("one");
        second.add("four");

        System.out.println("Is first contained in strings? " + strings.containsAll(first));
        System.out.println("Is second contained in strings? " + strings.containsAll(second));

// 2 addAll()
        System.out.println("*************** 2 ***************** ");

        boolean hasChanged = strings.addAll(first);

        System.out.println("Has strings changed? " + hasChanged);
        System.out.println("strings = " + strings);

// 3 removeAll()
        System.out.println("************* 3 ******************* ");

        Collection<String> toBeRemoved = new ArrayList<>();
        toBeRemoved.add("one");

        boolean hasChanged2 = strings.removeAll(toBeRemoved);

        System.out.println("Has strings changed? " + hasChanged2);
        System.out.println("strings = " + strings);


    // 4 retainAll()
        System.out.println("************* 4 ******************* ");


    Collection<String> toBeRetained = new ArrayList<>();
toBeRetained.add("one");
toBeRetained.add("four");

    boolean hasChanged3 = strings.retainAll(toBeRetained);

System.out.println("Has strings changed? " + hasChanged3);
System.out.println("strings = " + strings);



//********************  Méthodes qui gèrent la collection elle-même   ****************************

/// 5 isEmpty() and size()
        System.out.println("************* 5 ******************* ");

        if (!strings.isEmpty()) {
            System.out.println("Indeed strings is not empty!");
        }
        System.out.println("The number of elements in strings is " + strings.size());


/// 6  clear()
        System.out.println("************* 6 ******************* ");

    System.out.println("The number of elements in strings is " + strings.size());
  strings.clear();
 System.out.println("After clearing it, this number is now " + strings.size());


  ////////    parfois on aura besoin de  transformer les collections en tableau donc si c le cas :


        String[] tabString1 = strings.toArray(new String[] {}); // you can pass an empty array
        String[] tabString2 = strings.toArray(new String[15]);   // or an array of the right size





        /// 7 ********* Itérer sur les éléments d'une collection ********
        System.out.println("************* 7 ******************* ");

        Collection<String> strings1 = List.of("one", "two", "three");

        for (String element: strings1) {
            System.out.println(element);
        }

////// 8 ************  Utilisation d'un itérateur sur une collection  ******************

            System.out.println("************* 8 ******************* ");


            Collection<String> strings2 = List.of("one", "two", "three", "four");


            /////// cette ligne est equivalente à : for (int i = 0; i < strings2.size(); i++) {
            //    String element = strings2.get(i);

            for (Iterator<String> iterator = strings2.iterator(); iterator.hasNext();) {
                String e = iterator.next();
                if (e.length() == 3) {
                    System.out.println(e);
                }
            }


            // si on veut supprimer un element de la collection on utilise removeif() ;
            // ce code est faut : Iterator<String> iterator = strings.iterator();
            //while (iterator.hasNext()) {
            //
            //    String element = iterator.next();
            //    strings.remove(element);





            // 9 ***************** Implémentation de l'interface itérable :  ************
            record Range(int start, int end) implements Iterable<Integer> {

                @Override
                public Iterator<Integer> iterator() {
                    return new Iterator<>() {
                        private int index = start;

                        @Override
                        public boolean hasNext() {
                            return index < end;
                        }

                        @Override
                        public Integer next() {
                            if (index > end) {
                                throw new NoSuchElementException("" + index);
                            }
                            int currentIndex = index;
                            index++;
                            return currentIndex;
                        }
                    };
                }
            }

            // 10 ******************** Itérer sur les éléments d'une liste ***********************
        System.out.println("************* 10 ******************* ");

        List<String> numbers = Arrays.asList("one", "two", "three");
        for (ListIterator<String> iterator = numbers.listIterator(); iterator.hasNext();) {
            String nextElement = iterator.next();
            if (Objects.equals(nextElement, "two")) {
                iterator.set("2");
            }
        }
        System.out.println("numbers = " + numbers);



////// 11  *******************  SortedSet et NavigableSet  ******************************



        System.out.println("************* 11 ******************* ");

        List<String> list = List.of("one", "two", "three", "four", "five", "six");
        Set<String> set = new HashSet<>();
        set.addAll(list);
        set.forEach(System.out::println);




        //   SortedSetinterface ajoute de nouvelles méthodes à Set.

// first()et last()renvoie les éléments les plus petits et les plus grands de l'ensemble
//headSet(toElement)et tailSet(fromElement)vous renvoie des sous-ensembles contenant les éléments inférieurs toElementou supérieurs àfromElement
//subSet(fromElement, toElement)vous donne un sous-ensemble de l'élément compris entre fromElementet toElement.


        // exemple :
        System.out.println("************* sortedSet ******************* ");

        SortedSet<String> sortedSet = new TreeSet<>(Set.of("a", "b", "c", "d", "e", "f"));
        SortedSet<String> subSet = sortedSet.subSet("aa", "d");
        System.out.println("sub set = " + subSet);





///  NavigableSetil  permet également d'itérer sur ses éléments dans l'ordre décroissant

        System.out.println("************* NavigableSet ******************* ");


        NavigableSet<String> sortedStrings = new TreeSet<>(Set.of("a", "b", "c", "d", "e", "f"));
        System.out.println("sorted strings = " + sortedStrings);
        NavigableSet<String> reversedStrings = sortedStrings.descendingSet();
        System.out.println("reversed strings = " + reversedStrings);

//// Modification de l'ordre des éléments d'une liste :

// sort()trie la liste sur place
// shuffle()mélange aléatoirement les éléments de la liste fournie.
//rotate()fait tourner les éléments de la liste. Après une rotation, l'élément à l'index 0 se retrouvera à l'index 1 et ainsi de suite.



        // combiner subList()et rotate()pour supprimer un élément à un index donné et l'insérer à un autre endroit de la liste. Cela peut être fait avec le code suivant

        System.out.println("************* subList()et rotate() ******************* ");

        List<String> s = Arrays.asList("0", "1", "2", "3", "4");
        System.out.println(s);
        int fromIndex = 1, toIndex = 4;
        Collections.rotate(s.subList(fromIndex, toIndex), -1);
        System.out.println(s);



//               reverse():inverser l'ordre des éléments de la liste.
//               swap(): échange deux éléments de la liste. Cette méthode peut prendre une liste comme argument, ainsi qu'un simple tableau.





//  ********************* Stockage d'éléments dans des piles et des files d'attente **********

//push(element) : ajoute un élément à la file d'attente ou à la pile
//pop() : supprime un élément de la pile, c'est-à-dire l'élément le plus jeune ajouté
//poll() : supprime un élément de la file d'attente, c'est-à-dire l'élément le plus ancien ajouté
//peek() : permet de voir l'élément que vous obtiendrez avec un pop() ou un poll() , mais sans le supprimer de la file d'attente de la pile.





 // 12 ******************* Les maps *****************



        // Pas plus de 10 paires clé-valeur.
        Map<Integer, String> map =
                Map.of(
                        1, "one",
                        2, "two",
                        3, "three"
                );

        // Si c plus de 10 :
        Map.Entry<Integer, String> e1 = Map.entry(1, "one");
        Map.Entry<Integer, String> e2 = Map.entry(2, "two");
        Map.Entry<Integer, String> e3 = Map.entry(3, "three");
        Map<Integer, String> map2 = Map.ofEntries(e1, e2, e3);



/////

        Map<Integer, String> map3 = new HashMap<>();

        map3.put(1, "one");
        map3.put(2, "two");
        map3.put(3, "three");

        List<String> values = new ArrayList<>();
        for (int key = 0; key < 5; key++) {
            values.add(map3.getOrDefault(key,"UNDEFINED"));
        }

        System.out.println("values = " + values);



        ///// expression lambda dans les maps :
        System.out.println("************* Expression lambda dans les maps ******************* ");


        List<String> l = List.of("one", "two", "three", "four", "five", "six", "seven");
        Map<Integer, List<String>> m = new HashMap<>();
        for (String word: l) {
            int length = word.length();
            if (!m.containsKey(length)) {
                m.put(length, new ArrayList<>());
            }
            m.get(length).add(word);
        }

        map.forEach((key, value) -> System.out.println(key + " :: " + value));



/////////

        SortedMap<Integer, String> map1 = new TreeMap<>();
        map1.put(1, "one");
        map1.put(2, "two");
        map1.put(3, "three");
        map1.put(5, "five");
        map1.put(6, "six");

        SortedMap<Integer, String> headMap = map1.headMap(3);
        headMap.put(0, "zero"); // this line is ok
       // headMap.put(4, "four"); // this line throws an IllegalArgumentException



        ///// ********   Méthodes ajoutées par NavigableMap    *************


        //firstKey(), firstEntry(), lastEntry(), etlastKey(): renvoie la clé ou l'entrée la plus basse ou la plus grande de cette carte.
        //ceilingKey(key), ceilingEntry(key), higherKey(key), higherEntry(key): renvoie la clé la plus basse ou l'entrée supérieure à la clé fournie. Les ceilingméthodes peuvent renvoyer une clé égale à la clé fournie, alors que la clé renvoyée par les higherméthodes est strictement supérieure.
        //floorKey(key), floorEntry(key), lowerKey(key), lowerEntry(key): renvoie la plus grande clé ou l'entrée inférieure à la clé fournie. Les floorméthodes peuvent renvoyer une clé égale à la clé fournie, alors que la clé renvoyée par les higherméthodes est strictement inférieure.

//pollFirstEntry(): renvoie et supprime l'entrée la plus basse
//pollLastEntry(): renvoie et supprime la plus grande entrée.

/////// exemple :

        System.out.println("*************  NavigableMap ******************* ");

        NavigableMap<Integer, String> map0 = new TreeMap<>();
        map0.put(1, "one");
        map0.put(2, "two");
        map0.put(3, "three");
        map0.put(4, "four");
        map0.put(5, "five");

        map0.keySet().forEach(key -> System.out.print(key + " "));
        System.out.println();

        NavigableSet<Integer> descendingKeys = map0.descendingKeySet();
        descendingKeys.forEach(key -> System.out.print(key + " "));




    }

}

