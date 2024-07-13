package types

data class Polynomial(
    val monomials: List<Monomial>
)

fun polynomialFor(
    monomials: List<Monomial>
): Polynomial {
    val monomialsMap = mutableMapOf<Map<Char, UInt>, Monomial>()
    for (monomial in monomials) {
        val item = monomialsMap[monomial.powers]
        if (item == null) {
            monomialsMap[monomial.powers] = monomial
        } else {
            monomialsMap[monomial.powers] =
                monomialFor(item.coefficient + monomial.coefficient, monomial.powers)
        }
    }

    return Polynomial(
        monomialsMap.values
            .filter { it.coefficient != 0.0 }
            .sortedWith { a, b ->
                lexicographicOrder(a, b)
            }
    )
}

