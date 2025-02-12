package travelator.itinerary

import travelator.Location
import java.time.Duration

class Route(private val journeys: List<Journey>) {
    fun size(): Int = journeys.size

    operator fun get(index: Int): Journey = journeys[index]

    val departsFrom: Location
        get() = get(0).departsFrom

    val arrivesAt: Location
        get() = get(size() - 1).arrivesAt

    val duration: Duration
        get() = Duration.between(
            get(0).departureTime,
            get(size() - 1).arrivalTime
        )

    fun withJourneyAt(index: Int, replacedBy: Journey): Route {
        val newJourneys = ArrayList(this.journeys)
        newJourneys[index] = replacedBy
        return Route(newJourneys)
    }
}