package io

import types.Monomial
import types.Polynomial
import kotlin.math.abs

fun shouldPrintCoefficient(m: Monomial): Boolean {
    if (m.coefficient == 0.0) return true
    if (m.powers.size == 0) return true
    if (abs(m.coefficient) == 1.0) return false

    return true
}

fun printPolynomial(poly: Polynomial): String {
    val terms = mutableListOf<String>()

    var leadingMonomial = true
    for (monomial in poly.monomials) {
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
        for (power in monomial.powers) {
            if (power.value == 1u) {
                terms.add(power.key.toString())
            } else {
                terms.add(power.key + "^" + power.value)
            }
        }

        leadingMonomial = false
    }

    return terms.joinToString(" ")
}