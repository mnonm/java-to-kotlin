package travelator

import java.time.Duration

// ?: 좌변이 null이 아니면 좌변 값 반환, null이면 우변 값 반환
fun longestLegOver1(
    legs: List<Leg>,
    duration: Duration
): Leg? {
    var longestLeg: Leg? = legs.maxByOrNull(Leg::getPlannedDuration) ?:
    return null
    return if (longestLeg != null && longestLeg.plannedDuration > duration)
        longestLeg
    else
        null
}

// ?.let -> ?.는 수신 객체가 Null이면 null로 평가되고, 그렇지 않으면 let 블록으로 가장 긴 구간을 전달한다
fun longestLegOver2(
    legs: List<Leg>,
    duration: Duration
): Leg? =
    legs.maxByOrNull(Leg::getPlannedDuration)?.let { longestLeg ->
        if (longestLeg.plannedDuration > duration)
            longestLeg
        else
            null
    }

fun List<Leg>.longestLegOver(duration: Duration): Leg? {
    val longestLeg = maxByOrNull(Leg::getPlannedDuration)
    return when {
        longestLeg == null -> null
        longestLeg.plannedDuration > duration -> longestLeg
        else -> null
    }
}

// takeIf -> 술어가 true면 수신 객체를 반환, true가 아니면 null 반환
fun longestLegOver4(
    legs: List<Leg>,
    duration: Duration
): Leg? =
    legs.maxByOrNull(Leg::getPlannedDuration)?.takeIf { longestLeg ->
        longestLeg.plannedDuration > duration
    }