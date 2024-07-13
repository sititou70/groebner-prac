package arithmetic

import types.Monomial
import types.Polynomial


fun subPolynomial(p1: Polynomial, p2: Polynomial): Polynomial {
    val invertedP2 =
        Polynomial(
            p2.monomials
                .map { Monomial(-1.0 * it.coefficient, it.powers) }
                .toMutableList()
        )

    return addPolynomial(p1, invertedP2)
}
