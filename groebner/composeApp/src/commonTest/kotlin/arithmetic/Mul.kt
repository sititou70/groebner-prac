package arithmetic

import io.parsePolynomial
import io.printPolynomial
import kotlin.test.Test
import kotlin.test.assertEquals

class Mul {
    @Test
    fun zero1() {
        val p1 = parsePolynomial("2 x^2 y^3 + -3 y^4 + y + 1").getOrNull()!!
        val p2 = parsePolynomial("0").getOrNull()!!
        assertEquals(
            "0",
            printPolynomial(mulPolynomial(p1, p2))
        )
    }

    @Test
    fun zero2() {
        val p1 = parsePolynomial("0").getOrNull()!!
        val p2 = parsePolynomial("2 x^2 y^3 + -3 y^4 + y + 1").getOrNull()!!
        assertEquals(
            "0",
            printPolynomial(mulPolynomial(p1, p2))
        )
    }

    @Test
    fun singleVariable1() {
        val p1 = parsePolynomial("x + 2").getOrNull()!!
        val p2 = parsePolynomial("x + 3").getOrNull()!!
        assertEquals(
            "x^2 + 5 x + 6",
            printPolynomial(mulPolynomial(p1, p2))
        )
    }

    @Test
    fun singleVariable2() {
        val p1 = parsePolynomial("2 x + 3").getOrNull()!!
        val p2 = parsePolynomial("2 x - 3").getOrNull()!!
        assertEquals(
            "4 x^2 - 9",
            printPolynomial(mulPolynomial(p1, p2))
        )
    }

    @Test
    fun multiVariable() {
        val p1 = parsePolynomial("2 x^2 y^3 + -3 y^4 + y + 1").getOrNull()!!
        val p2 = parsePolynomial("2 x + 4 a + 6 y + b - 3 z - 5 c").getOrNull()!!
        assertEquals(
            "8 a x^2 y^3 - 12 a y^4 + 4 a y + 4 a + 2 b x^2 y^3 - 3 b y^4 + b y + b - 10 c x^2 y^3 + 15 c y^4 - 5 c y - 5 c + 4 x^3 y^3 + 12 x^2 y^4 - 6 x^2 y^3 z - 6 x y^4 + 2 x y + 2 x - 18 y^5 + 9 y^4 z + 6 y^2 - 3 y z + 6 y - 3 z",
            printPolynomial(mulPolynomial(p1, p2))
        )
    }
}
