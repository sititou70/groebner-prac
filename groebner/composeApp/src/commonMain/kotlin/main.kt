import io.parsePolynomial
import io.printPolynomial

@JsExport
fun groebnerBasisString(inputs: String): String {
    return groebnerBasis(
        inputs
            .split(",")
            .map { parsePolynomial(it).getOrThrow() }
            .toSet()
    )
        .map { printPolynomial(it) }
        .joinToString(",")
}
