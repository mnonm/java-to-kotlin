package travelator;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static travelator.Collections.sorted;
import static travelator.Other.SOME_COMPLICATED_RESULT;
import static travelator.Other.routesFor;
import static travelator.Routes.getDepartsFrom;

public class Suffering {

    public static int sufferScoreFor(List<Journey> route) {
        return sufferScore(
            longestJourneysIn(route, 3),
            getDepartsFrom(route)
        );
    }

    public static List<Journey> longestJourneysIn(
        List<Journey> journeys,
        int limit
    ) {
        var actualLimit = Math.min(journeys.size(), limit);
        return sorted(
            journeys,
            comparing(Journey::getDuration).reversed()
        ).subList(0, actualLimit);
    }

    public static List<List<Journey>> routesToShowFor(String itineraryId) {
        return bearable(routesFor(itineraryId));
    }

    private static List<List<Journey>> bearable(List<List<Journey>> routes) {
       return routes.stream()
           .filter(route -> sufferScoreFor(route) <= 10)
           .collect(Collectors.toUnmodifiableList());
    }

    private static int sufferScore(
        List<Journey> longestJourneys,
        Location start
    ) {
        return SOME_COMPLICATED_RESULT();
    }
}