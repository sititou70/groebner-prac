package arithmetic

import types.Monomial
import types.Polynomial

fun mulMonomial(m1: Monomial, m2: Monomial): Monomial {
    val powers = HashMap(m2.powers)
    for (entry in m1.powers) {
        val m1Variable = entry.key
        val m1Power = entry.value

        powers[m1Variable] = m1Power + (powers[m1Variable] ?: 0u)
    }

    return Monomial(m1.coefficient * m2.coefficient, powers)
}

fun mulPolynomialByMonomial(p: Polynomial, m: Monomial): Polynomial {
    return Polynomial(p.monomials.map { mulMonomial(it, m) }.filter { it.coefficient != 0.0 })
}

fun mulPolynomial(p1: Polynomial, p2: Polynomial): Polynomial {
    var muled = Polynomial(listOf())
    for (p1Monomial in p1.monomials) {
        muled = addPolynomial(muled, mulPolynomialByMonomial(p2, p1Monomial))
    }

    return muled
}
