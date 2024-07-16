import io.parsePolynomial
import io.printPolynomial
import kotlin.test.Test
import kotlin.test.assertEquals

class GroebnerTest {
    @Test
    fun questions() {
        val questions =
            sampleQuestions
                .split("---")
                .map { it.split("\n\n") }
                .map { splited ->
                    object {
                        val basis = splited[2].split("\n").map { parsePolynomial(it).getOrThrow() }.toSet()
                        val groebnerBasis = splited[3].split("\n").toSet()
                    }
                }

        for (question in questions) {
            val basis = groebnerBasis(question.basis)
            assertEquals(question.groebnerBasis, basis.map { printPolynomial(it) }.toSet())
        }
    }
}
