package arithmetic

import types.Monomial
import types.Polynomial
import types.monomialFor
import types.polynomialFor

fun divMonomial(m1: Monomial, m2: Monomial): Result<Monomial> {
    if (m2.coefficient == 0.0) return Result.failure(Exception())
    if (m1.coefficient == 0.0) return Result.success(Monomial(0.0, hashMapOf()))

    val power = hashMapOf<Char, UInt>()
    for (variable in m1.powers.keys + m2.powers.keys) {
        if ((m1.powers[variable] ?: 0u) < (m2.powers[variable] ?: 0u))
            return Result.failure(Exception())

        power[variable] = (m1.powers[variable] ?: 0u) - (m2.powers[variable] ?: 0u)
    }

    return Result.success(monomialFor(m1.coefficient / m2.coefficient, power))
}

fun reducePolynomial(p: Polynomial, ps: List<Polynomial>): Pair<List<Polynomial>, Polynomial> {
    val quotients = ps.map { Polynomial(listOf()) }.toMutableList()
    var remainder = Polynomial(listOf())

    var restOfP = polynomialFor(p.monomials.toList())
    while (restOfP.monomials.isNotEmpty()) {
        var divided = false
        for ((psItemIndex, psItem) in ps.withIndex()) {
            val lTRestOfPDividedByLTPsItem = divMonomial(restOfP.monomials[0], psItem.monomials[0])
            if (lTRestOfPDividedByLTPsItem.isSuccess) {
                restOfP =
                    subPolynomial(
                        restOfP,
                        mulPolynomial(psItem, polynomialFor(listOf(lTRestOfPDividedByLTPsItem.getOrThrow())))
                    )
                quotients[psItemIndex] =
                    addPolynomial(
                        quotients[psItemIndex],
                        polynomialFor(listOf(lTRestOfPDividedByLTPsItem.getOrThrow()))
                    )
                divided = true
                break
            }
        }

        if (!divided) {
            remainder = addPolynomial(remainder, polynomialFor(listOf(restOfP.monomials[0])))
            restOfP = subPolynomial(restOfP, polynomialFor(listOf(restOfP.monomials[0])))
        }
    }

    return Pair(quotients, remainder)
}
