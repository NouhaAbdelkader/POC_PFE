import java.util.*;
import java.util.stream.Collectors;

public class Record {

    // Définition des records City et State
    public record City(String name, State state) {}
        public record State(String name) {}

        public static void main(String[] args) {
        // Création de quelques États
        State state1 = new State("California");
        State state2 = new State("Texas");
        State state3 = new State("New York");

        // Création d'une liste de villes appartenant à ces États
        List<City> cities = List.of(
                new City("Los Angeles", state1),
                new City("San Francisco", state1),
                new City("San Diego", state1),
                new City("Houston", state2),
                new City("Dallas", state2),
                new City("Austin", state2),
                new City("New York City", state3),
                new City("Buffalo", state3)
        );

        // Utilisation de Map et Stream pour compter les villes par État
        Map<State, Long> numberOfCitiesPerState = cities.stream()
                .collect(Collectors.groupingBy(City::state, Collectors.counting()));

        // Affichage du nombre de villes par État
        numberOfCitiesPerState.forEach((state, count) ->
                System.out.println(state.name() + " a " + count + " villes."));

        // Trouver l'État avec le plus grand nombre de villes
        Optional<Map.Entry<State, Long>> stateWithMostCities = numberOfCitiesPerState.entrySet().stream()
                .max(Map.Entry.comparingByValue());

        // Affichage de l'État avec le plus grand nombre de villes
        stateWithMostCities.ifPresent(entry ->
                System.out.println("L'État avec le plus de villes est : " + entry.getKey().name() + " avec " + entry.getValue() + " villes."));
    }
}
