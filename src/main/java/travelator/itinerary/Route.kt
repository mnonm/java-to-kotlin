package travelator.itinerary

import travelator.Location
import java.time.Duration

typealias Route = List<Journey>

fun Route(journeys: List<Journey>) = journeys

val Route.duration: Duration
    get() = Duration.between(
        first().departureTime,
        last().arrivalTime
    )

val Route.arrivesAt: Location
    get() = last().arrivesAt

val Route.departsFrom: Location
    get() = first().departsFrom

fun <T> Iterable<T>.withItemAt(index: Int, replacedBy: T): List<T> =
    this.toMutableList().apply {
        this[index] = replacedBy
    }