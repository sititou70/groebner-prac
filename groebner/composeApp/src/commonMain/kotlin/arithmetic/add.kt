package arithmetic

import lexicographicOrder
import types.Monomial
import types.Polynomial

fun addMonomialToPolynomial(m: Monomial, p: Polynomial): Polynomial {
    val added = mutableListOf<Monomial>()
    var isAdded = false
    for (monomial in p.monomials) {
        if (monomial.powers == m.powers && !isAdded) {
            if (m.coefficient + monomial.coefficient != 0.0)
                added.add(Monomial(m.coefficient + monomial.coefficient, m.powers))
            isAdded = true
        } else {
            added.add(monomial)
        }
    }

    if (!isAdded)
        added.add(m)

    val sortedAdded = added.sortedWith { a, b ->
        lexicographicOrder(a, b)
    }.toMutableList()


    return Polynomial(sortedAdded)
}

fun addPolynomial(p1: Polynomial, p2: Polynomial): Polynomial {
    var added = p2
    for (p1Monomial in p1.monomials) {
        added = addMonomialToPolynomial(p1Monomial, added)
    }

    return added
}
