package io

import types.Monomial
import types.Polynomial
import kotlin.math.abs

fun shouldPrintCoefficient(m: Monomial): Boolean {
    if (m.coefficient == 0.0) return true
    if (m.powers.values.fold(0u) { x, y -> x + y } == 0u) return true
    if (abs(m.coefficient) == 1.0) return false

    return true
}

fun printPolynomial(p: Polynomial): String {
    if (p.monomials.isEmpty()) return "0"

    val terms = mutableListOf<String>()

    var leadingMonomial = true
    for (monomial in p.monomials) {
        // sign
        if (monomial.coefficient >= 0) {
            if (!leadingMonomial)
                terms.add("+")
        } else {
            terms.add("-")
        }

        // coefficient
        if (shouldPrintCoefficient(monomial)) {
            terms.add(abs(monomial.coefficient).toString())
        }

        // powers
        if (monomial.coefficient != 0.0) {
            for (power in monomial.powers.entries.sortedBy { it.key }) {
                if (power.value == 0u) {
                    // do nothing
                } else if (power.value == 1u) {
                    terms.add(power.key.toString())
                } else {
                    terms.add(power.key + "^" + power.value)
                }
            }
        }

        leadingMonomial = false
    }

    return terms.joinToString(" ")
}