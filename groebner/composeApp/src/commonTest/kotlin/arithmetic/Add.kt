package arithmetic

import io.parsePolynomial
import io.printPolynomial
import kotlin.test.Test
import kotlin.test.assertEquals

class Add {
    @Test
    fun case1() {
        val p1 = parsePolynomial("2 x^2 y + 1").getOrNull()!!
        val p2 = parsePolynomial("3 x").getOrNull()!!
        assertEquals(
            "2 x^2 y + 3 x + 1",
            printPolynomial(addPolynomial(p1, p2))
        )
    }

    @Test
    fun case2() {
        val p1 = parsePolynomial("2 x^2 y + 1").getOrNull()!!
        val p2 = parsePolynomial("3 x^2 y").getOrNull()!!
        assertEquals(
            "5 x^2 y + 1",
            printPolynomial(addPolynomial(p1, p2))
        )
    }

    @Test
    fun case3() {
        val p1 = parsePolynomial("2 x^2 y + 1").getOrNull()!!
        val p2 = parsePolynomial("-2 x^2 y").getOrNull()!!
        assertEquals(
            "1",
            printPolynomial(addPolynomial(p1, p2))
        )
    }

    @Test
    fun case4() {
        val p1 = parsePolynomial("y^2").getOrNull()!!
        val p2 = parsePolynomial("z").getOrNull()!!
        assertEquals(
            "y^2 + z",
            printPolynomial(addPolynomial(p1, p2))
        )
    }

    @Test
    fun zeroPower() {
        val p1 = parsePolynomial("x^0 y^0").getOrNull()!!
        val p2 = parsePolynomial("z^0").getOrNull()!!
        assertEquals(
            "2",
            printPolynomial(addPolynomial(p1, p2))
        )
    }
}
