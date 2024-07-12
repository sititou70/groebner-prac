package io

import types.Polynomial
import kotlin.math.abs

fun printPolynomial(poly: Polynomial): String {
    val terms = mutableListOf<String>()

    var leadingMono = true
    for (mono in poly.monomials) {
        if (!leadingMono) terms.add(if (mono.coefficient >= 0) "+" else "-")

        if (abs(mono.coefficient) == 1L) {
            if (mono.powers.size == 0)
                terms.add("1")
        } else {
            if (leadingMono) {
                terms.add(mono.coefficient.toString())
            } else {
                terms.add(abs(mono.coefficient).toString())
            }
        }

        for (power in mono.powers) {
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