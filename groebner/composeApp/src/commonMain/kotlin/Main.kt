import io.parsePolynomial
import io.printPolynomial

@OptIn(ExperimentalJsExport::class)
@JsExport
fun groebnerBasisString(inputs: String): String =
    groebnerBasis(
        inputs
            .split(",")
            .map { parsePolynomial(it).getOrThrow() }
            .toSet(),
    ).joinToString(",") { printPolynomial(it) }
