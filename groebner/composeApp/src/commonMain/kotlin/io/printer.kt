package io

import types.Monomial
import types.Polynomial
import kotlin.math.abs

fun shouldPrintCoefficient(m: Monomial): Boolean {
    if (m.powers.isEmpty()) return true
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
            for ((variable, power) in monomial.powers.entries.sortedBy { it.key }) {
                if (power == 1u) {
                    terms.add(variable.toString())
                } else {
                    terms.add("$variable^$power")
                }
            }
        }

        leadingMonomial = false
    }

    return terms.joinToString(" ")
}