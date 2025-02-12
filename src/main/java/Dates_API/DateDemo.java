package Dates_API;

import java.time.*;
import java.time.chrono.*;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.*;
import java.util.*;

/**
 * Exemple démontrant les différentes API de dates et d'heures en Java.
 */
public class DateDemo {

    public static void main(String[] args) {

        // Affichage de l'instant actuel (temps machine)
        Instant nowInstant = Instant.now();
        System.out.println("Instant: " + nowInstant);

        // Affichage de l'heure locale actuelle (temps humain sans zone horaire)
        LocalDateTime nowLocalDateTime = LocalDateTime.now();
        System.out.println("LocalDateTime: " + nowLocalDateTime);

        // Affichage de l'heure locale actuelle avec zone horaire
        ZonedDateTime nowZonedDateTime = ZonedDateTime.now();
        System.out.println("ZonedDateTime: " + nowZonedDateTime);

        // Exemple d'une durée de 5 heures
        Duration duration = Duration.ofHours(5);
        System.out.println("Duration: " + duration);

        // Exemple d'une période entre deux dates
        LocalDate startDate = LocalDate.of(2020, 1, 1);
        LocalDate endDate = LocalDate.of(2023, 1, 1);
        Period period = Period.between(startDate, endDate);
        System.out.println("Period: " + period);

        // Affichage du jour de la semaine avec différents styles
        DayOfWeek dow = DayOfWeek.MONDAY;
        Locale locale = Locale.getDefault();
        System.out.printf("3 days after Monday: %s%n", dow.plus(3)); // Affiche jeudi
        System.out.println(dow.getDisplayName(TextStyle.FULL, locale));   // Exemple: "Monday"
        System.out.println(dow.getDisplayName(TextStyle.NARROW, locale)); // Exemple: "M"
        System.out.println(dow.getDisplayName(TextStyle.SHORT, locale));  // Exemple: "Mon"

        // Affichage des informations sur le mois de février
        Month month = Month.FEBRUARY;
        System.out.printf("Max length of February: %d%n", month.maxLength()); // Affiche 29 (année bissextile)
        System.out.println(month.getDisplayName(TextStyle.FULL, locale));   // Exemple: "February"
        System.out.println(month.getDisplayName(TextStyle.NARROW, locale)); // Exemple: "F"
        System.out.println(month.getDisplayName(TextStyle.SHORT, locale));  // Exemple: "Feb"

        // Exemple avec LocalDate : trouver le mercredi suivant
        LocalDate date = LocalDate.of(2000, Month.NOVEMBER, 20);
        LocalDate nextWed = date.with(TemporalAdjusters.next(DayOfWeek.WEDNESDAY));
        System.out.printf("For the date of %s, the next Wednesday is %s.%n", date, nextWed);

        // Obtenir le jour de la semaine d'une date spécifique
        DayOfWeek dotw = LocalDate.of(2012, Month.JULY, 9).getDayOfWeek();
        System.out.println("Day of the week: " + dotw); // Affiche Lundi

        // Exemple avec YearMonth : longueur du mois
        YearMonth date1 = YearMonth.now();
        System.out.printf("%s: %d%n", date1, date1.lengthOfMonth());

        // Exemple de MonthDay : vérification de la validité du 29 février pour une année non bissextile
        MonthDay dateLeap = MonthDay.of(Month.FEBRUARY, 29);
        boolean validLeapYear = dateLeap.isValidYear(2010);
        System.out.println("Is 29th February 2010 valid? " + validLeapYear); // Affiche false

        // Exemple avec Year : vérification d'une année bissextile
        boolean isLeap = Year.of(2012).isLeap();
        System.out.println("Is 2012 a leap year? " + isLeap); // Affiche true

        // Utilisation de DateTimeFormatter pour formater une date et heure
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM d yyyy  hh:mm a");
        LocalDateTime leaving = LocalDateTime.of(2013, Month.JULY, 20, 19, 30);
        ZoneId leavingZone = ZoneId.of("America/Los_Angeles");
        ZonedDateTime departure = ZonedDateTime.of(leaving, leavingZone);

        // Formatage de la date de départ
        try {
            String out1 = departure.format(format);
            System.out.printf("LEAVING:  %s (%s)%n", out1, leavingZone);
        } catch (DateTimeException exc) {
            System.out.printf("%s can't be formatted!%n", departure);
            throw exc;
        }

        // Calcul de l'heure d'arrivée après un vol de 10h50
        ZoneId arrivingZone = ZoneId.of("Asia/Tokyo");
        ZonedDateTime arrival = departure.withZoneSameInstant(arrivingZone).plusMinutes(650);

        try {
            String out2 = arrival.format(format);
            System.out.printf("ARRIVING: %s (%s)%n", out2, arrivingZone);
        } catch (DateTimeException exc) {
            System.out.printf("%s can't be formatted!%n", arrival);
            throw exc;
        }

        // Affichage de l'heure d'été ou de l'heure standard selon la zone horaire
        if (arrivingZone.getRules().isDaylightSavings(arrival.toInstant())) {
            System.out.printf("  (%s daylight saving time will be in effect.)%n", arrivingZone);
        } else {
            System.out.printf("  (%s standard time will be in effect.)%n", arrivingZone);
        }

        // Trouver le dernier jeudi de juillet 2013
        LocalDateTime localDate = LocalDateTime.of(2013, Month.JULY, 20, 19, 30);
        ZoneOffset offset = ZoneOffset.of("-08:00");
        OffsetDateTime offsetDate = OffsetDateTime.of(localDate, offset);
        OffsetDateTime lastThursday = offsetDate.with(TemporalAdjusters.lastInMonth(DayOfWeek.THURSDAY));

        System.out.printf("The last Thursday in July 2013 is the %sth.%n", lastThursday.getDayOfMonth());

        // Affichage de l'instant avec formatage
        Instant timestamp = Instant.now();
        LocalDateTime ldt = LocalDateTime.ofInstant(timestamp, ZoneId.systemDefault());
        System.out.printf("%s %d %d at %d:%d%n", ldt.getMonth(), ldt.getDayOfMonth(), ldt.getYear(), ldt.getHour(), ldt.getMinute());

        // Calcul de l'âge en années, mois, jours
        LocalDate today = LocalDate.now();
        LocalDate birthday = LocalDate.of(1960, Month.JANUARY, 1);
        Period p = Period.between(birthday, today);
        long p2 = ChronoUnit.DAYS.between(birthday, today);
        System.out.println("You are " + p.getYears() + " years, " + p.getMonths() + " months, and " + p.getDays() +
                " days old. (" + p2 + " days total)");
    }
}
