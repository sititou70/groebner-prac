package arithmetic

import types.Polynomial
import types.polynomialOf

fun addPolynomial(p1: Polynomial, p2: Polynomial): Polynomial {
    var added = p2
    for (p1Monomial in p1.monomials) {
        added = polynomialOf(added.monomials + listOf(p1Monomial))
    }

    return added
}
