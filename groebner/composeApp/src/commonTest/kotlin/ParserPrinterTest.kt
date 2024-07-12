import io.parsePolynomial
import io.printPolynomial
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class ParserPrinterTest {
    @Test
    fun spaces() {
        var parsed = parsePolynomial("   +  -2  x^2  ").getOrNull()
        assertNotNull(parsed)
        assertEquals("-2 x^2", printPolynomial(parsed))
    }

    @Test
    fun poly() {
        var parsed = parsePolynomial("2 x^2 y^3 + -3 y^4 + y + 1").getOrNull()
        assertNotNull(parsed)
        assertEquals("2 x^2 y^3 - 3 y^4 + y + 1", printPolynomial(parsed))
    }

    @Test
    fun numbers() {
        var parsed = parsePolynomial("1 + 2 - 3 - -4 - 5").getOrNull()
        assertNotNull(parsed)
        assertEquals("1 + 2 - 3 + 4 - 5", printPolynomial(parsed))
    }

    @Test
    fun variables() {
        var parsed = parsePolynomial("x + y - z + a + b - c").getOrNull()
        assertNotNull(parsed)
        assertEquals("x + y - z + a + b - c", printPolynomial(parsed))
    }
}