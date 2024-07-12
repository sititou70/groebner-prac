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

    var leadingMono = true
    for (monomial in poly.monomials) {
        // sign
        if (!leadingMono) terms.add(if (monomial.coefficient >= 0) "+" else "-")
        val signPrinted = !leadingMono

        // coefficient
        if (shouldPrintCoefficient(monomial)) {
            if (signPrinted) {
                terms.add(abs(monomial.coefficient).toString())
            } else {
                terms.add(monomial.coefficient.toString())
            }
        }

        // powers
        for (power in monomial.powers) {
            if (power.value == 1u) {
                terms.add(power.key.toString())
            } else {
                terms.add(power.key + "^" + power.value)
            }
        }

        leadingMono = false
    }

    return terms.joinToString(" ")
}