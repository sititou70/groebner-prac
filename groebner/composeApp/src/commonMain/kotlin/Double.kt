import kotlin.math.abs
import kotlin.math.roundToLong

const val EPSILON = 0.0000000001

fun doubleEquals(
    d1: Double,
    d2: Double,
): Boolean = abs(d1 - d2) < EPSILON

fun roundDouble(d: Double): Double = (d / EPSILON).roundToLong().toDouble() * EPSILON
