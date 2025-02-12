package travelator;

import java.util.List;
import travelator.itinerary.Journey;
import travelator.itinerary.RouteKt;

import java.time.Duration;

public class UI {

    public void render(Iterable<Journey> route) {
        for (var journey : route) {
            render(journey);
        }
    }


    public void render(List<Journey> route) {
        for (int i = 0; i < route.size(); i++) {
            var journey = route.get(i);
            render(journey);
        }
    }

    public void renderWithHeader(List<Journey> route) {
        renderHeader(
            RouteKt.getDepartsFrom(route), // <1>
            RouteKt.getArrivesAt(route),
            RouteKt.getDuration(route)
        );
        for (Journey journey : route) {
            render(journey);
        }
    }

    private void render(Journey journey) {
    }

    private void renderHeader(
        Location departsFrom,
        Location arrivesAt,
        Duration duration) {
    }
}