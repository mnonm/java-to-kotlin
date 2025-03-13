package travelator.util

import java.util.*

fun <T> T?.toOptional(): Optional<T & Any> = Optional.ofNullable(this)