import kotlin.math.abs
import kotlin.math.roundToLong

val epsilon = 0.0000000001

fun doubleEquals(d1: Double, d2: Double): Boolean = abs(d1 - d2) < epsilon

fun roundDouble(d: Double): Double = (d / epsilon).roundToLong().toDouble() * epsilon
