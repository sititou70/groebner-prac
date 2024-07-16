import io.parsePolynomial
import io.printPolynomial
import types.Polynomial
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class ParserPrinterTest {
    @Test
    fun spaces() {
        val parsed = parsePolynomial("   +  -2  x^2  ").getOrNull()
        assertNotNull(parsed)
        assertEquals("- 2 x^2", printPolynomial(parsed))
    }

    @Test
    fun poly() {
        val parsed = parsePolynomial("2 x^2 y^3 + -3 y^4 + y + 1").getOrNull()
        assertNotNull(parsed)
        assertEquals("2 x^2 y^3 - 3 y^4 + y + 1", printPolynomial(parsed))
    }

    @Test
    fun numbers() {
        val parsed = parsePolynomial("1 + 2 - 3 - -4 - 5").getOrNull()
        assertNotNull(parsed)
        assertEquals("- 1", printPolynomial(parsed))
    }

    @Test
    fun variables() {
        val parsed = parsePolynomial("x + a + y + b - z - c").getOrNull()
        assertNotNull(parsed)
        assertEquals("a + b - c + x + y - z", printPolynomial(parsed))
    }

    @Test
    fun zero1() {
        val parsed = parsePolynomial("0").getOrNull()
        assertNotNull(parsed)
        assertEquals("0", printPolynomial(parsed))
    }

    @Test
    fun zero2() {
        assertEquals("0", printPolynomial(Polynomial(listOf())))
    }

    @Test
    fun zeroPower() {
        val parsed = parsePolynomial("1 x^0 y^0 z^0").getOrNull()
        assertNotNull(parsed)
        assertEquals("1", printPolynomial(parsed))
    }

    @Test
    fun leadingMinusCoefficient() {
        val parsed = parsePolynomial("- 1").getOrNull()
        assertNotNull(parsed)
        assertEquals("- 1", printPolynomial(parsed))
    }

    @Test
    fun leadingMinusVariable() {
        val parsed = parsePolynomial("- x").getOrNull()
        assertNotNull(parsed)
        assertEquals("- x", printPolynomial(parsed))
    }

    @Test
    fun variableSorting() {
        val parsed = parsePolynomial("c b a").getOrNull()
        assertNotNull(parsed)
        assertEquals("a b c", printPolynomial(parsed))
    }
}
