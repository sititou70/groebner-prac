package arithmetic

import io.parsePolynomial
import io.printPolynomial
import kotlin.test.Test
import kotlin.test.assertEquals

class Div {
    @Test
    fun singleVariable1() {
        // see: https://www.lab2.toho-u.ac.jp/sci/is/tsuchiya/Introduction%20to%20Grobner%20basis.pdf
        val p = parsePolynomial("x^2 + 2 x + 1").getOrNull()!!
        val ps =
            listOf(
                parsePolynomial("2 x + 1").getOrNull()!!,
            )
        val (quotients, remainder) = reducePolynomial(p, ps)
        assertEquals(
            "0.5 x + 0.75",
            printPolynomial(quotients[0]),
        )
        assertEquals(
            "0.25",
            printPolynomial(remainder),
        )
    }

    @Test
    fun multiVariable1() {
        // see: https://www.lab2.toho-u.ac.jp/sci/is/tsuchiya/Introduction%20to%20Grobner%20basis.pdf
        val p = parsePolynomial("x^2 y + x y^2 + y^2").getOrNull()!!
        val ps =
            listOf(
                parsePolynomial("x y - 1").getOrNull()!!,
                parsePolynomial("y^2 - 1").getOrNull()!!,
            )
        val (quotients, remainder) = reducePolynomial(p, ps)
        assertEquals(
            "x + y",
            printPolynomial(quotients[0]),
        )
        assertEquals(
            "1",
            printPolynomial(quotients[1]),
        )
        assertEquals(
            "x + y + 1",
            printPolynomial(remainder),
        )
    }

    @Test
    fun multiVariable2() {
        // (x^2 - x + 1)(y^3 + y - 2)
        val p = parsePolynomial("x^2 y^3 + x^2 y - 2 x^2 - x y^3 - x y + 2 x + y^3 + y - 2").getOrNull()!!
        val ps =
            listOf(
                parsePolynomial("y^3 + y - 2").getOrNull()!!,
            )
        val (quotients, remainder) = reducePolynomial(p, ps)
        assertEquals(
            "x^2 - x + 1",
            printPolynomial(quotients[0]),
        )
        assertEquals(
            "0",
            printPolynomial(remainder),
        )
    }

    @Test
    fun multiVariable3() {
        // see: https://www.wolframalpha.com/input?i=PolynomialReduce%5Bx%5E3+y%5E2+-+x%5E3+z+%2B+x%5E2+y%5E2+z%5E2+-+x%5E2+z%5E3+%2B+x+y%5E3+-+x+y+z+%2B+y%5E3+z%5E2+-+y+z%5E3%2C+%7Bx%5E2+%2B+1%2C+y%5E2+%2B+2%2C+z+%2B+3%7D%5D&lang=ja

        // (x^2 + y)(y^2 - z)(z^2 + x)
        val p =
            parsePolynomial("x^3 y^2 - x^3 z + x^2 y^2 z^2 - x^2 z^3 + x y^3 - x y z + y^3 z^2 - y z^3").getOrNull()!!
        val ps =
            listOf(
                parsePolynomial("x^2 + 1").getOrNull()!!,
                parsePolynomial("y^2 + 2").getOrNull()!!,
                parsePolynomial("z + 3").getOrNull()!!,
            )
        val (quotients, remainder) = reducePolynomial(p, ps)
        assertEquals(
            "x y^2 - x z + y^2 z^2 - z^3",
            printPolynomial(quotients[0]),
        )
        assertEquals(
            "x y - x + y z^2 - z^2",
            printPolynomial(quotients[1]),
        )
        assertEquals(
            "- x y + x - y z^2 + y z - 3 y + z^2 - z + 3",
            printPolynomial(quotients[2]),
        )
        assertEquals(
            "x y - x + 9 y - 9",
            printPolynomial(remainder),
        )
    }
}
