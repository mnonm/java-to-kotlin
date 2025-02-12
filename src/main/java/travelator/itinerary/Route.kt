package travelator.itinerary

import travelator.Location
import java.time.Duration

class Route(val journeys: List<Journey>)

val Route.duration: Duration
    get() = Duration.between(
        get(0).departureTime,
        get(size - 1).arrivalTime
    )

val Route.arrivesAt: Location
    get() = get(size - 1).arrivesAt

operator fun Route.get(index: Int): Journey = journeys[index]

val Route.size: Int
    get() = journeys.size

fun Route.withJourneyAt(index: Int, replacedBy: Journey): Route {
    val newJourneys = ArrayList(journeys)
    newJourneys[index] = replacedBy
    return Route(newJourneys)
}

val Route.departsFrom: Location
    get() = get(0).departsFrom