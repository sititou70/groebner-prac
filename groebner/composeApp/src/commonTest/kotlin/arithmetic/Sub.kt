package arithmetic

import io.parsePolynomial
import io.printPolynomial
import kotlin.test.Test
import kotlin.test.assertEquals

class Sub {
    @Test
    fun case1() {
        val p1 = parsePolynomial("2 x^2 y + 1").getOrNull()!!
        val p2 = parsePolynomial("3 x").getOrNull()!!
        assertEquals(
            "2 x^2 y - 3 x + 1",
            printPolynomial(subPolynomial(p1, p2))
        )
    }

    @Test
    fun case2() {
        val p1 = parsePolynomial("2 x^2 y + 1").getOrNull()!!
        val p2 = parsePolynomial("3 x^2 y").getOrNull()!!
        assertEquals(
            "- x^2 y + 1",
            printPolynomial(subPolynomial(p1, p2))
        )
    }

    @Test
    fun case3() {
        val p1 = parsePolynomial("-2 x^2 y + 1").getOrNull()!!
        val p2 = parsePolynomial("-2 x^2 y").getOrNull()!!
        assertEquals(
            "1",
            printPolynomial(subPolynomial(p1, p2))
        )
    }
}
