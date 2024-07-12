import types.Monomial
import kotlin.test.Test
import kotlin.test.assertEquals

class OrderTest {
    @Test
    fun case1() {
        assertEquals(
            1,
            lexicographicOrder(
                Monomial(1, hashMapOf('x' to 1u, 'y' to 2u, 'z' to 3u)),
                Monomial(1, hashMapOf('a' to 1u))
            )
        )
    }

    @Test
    fun case2() {
        assertEquals(
            -1,
            lexicographicOrder(
                Monomial(1, hashMapOf('x' to 1u, 'y' to 2u, 'z' to 3u)),
                Monomial(1, hashMapOf('y' to 3u, 'z' to 3u)),
            )
        )
    }

    @Test
    fun case3() {
        assertEquals(
            0,
            lexicographicOrder(
                Monomial(1, hashMapOf('x' to 1u, 'y' to 2u, 'z' to 3u)),
                Monomial(1, hashMapOf('x' to 1u, 'y' to 2u, 'z' to 3u)),
            )
        )
    }

    @Test
    fun case4() {
        assertEquals(
            -1,
            lexicographicOrder(
                Monomial(1, hashMapOf('a' to 1u, 'y' to 2u, 'z' to 3u)),
                Monomial(1, hashMapOf('b' to 1u))
            )
        )
    }

    @Test
    fun case5() {
        assertEquals(
            -1,
            lexicographicOrder(
                Monomial(1, hashMapOf('x' to 1u, 'y' to 2u, 'z' to 4u)),
                Monomial(1, hashMapOf('x' to 1u, 'y' to 2u, 'z' to 3u)),
            )
        )
    }

}
