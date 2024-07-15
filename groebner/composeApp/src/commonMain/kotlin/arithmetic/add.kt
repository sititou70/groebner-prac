package arithmetic

import types.Polynomial
import types.polynomialOf

fun addPolynomial(p1: Polynomial, p2: Polynomial): Polynomial {
    return polynomialOf(p1.monomials + p2.monomials)
}