package types

import doubleEquals

data class Polynomial(
    val monomials: List<Monomial>
)

fun polynomialOf(
    monomials: List<Monomial>
): Polynomial {
    val monomialsMap = mutableMapOf<Map<Char, UInt>, Monomial>()
    for (monomial in monomials) {
        val item = monomialsMap[monomial.powers]
        if (item == null) {
            monomialsMap[monomial.powers] = monomial
        } else {
            monomialsMap[monomial.powers] =
                monomialOf(item.coefficient + monomial.coefficient, monomial.powers)
        }
    }

    return Polynomial(
        monomialsMap.values
            .filter { !doubleEquals(it.coefficient, 0.0) }
            .sortedWith { a, b ->
                lexicographicOrder(a, b)
            }
    )
}
