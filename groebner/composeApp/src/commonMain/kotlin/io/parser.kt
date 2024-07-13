package io

import types.Monomial
import types.Polynomial
import types.monomialOf
import types.polynomialOf

enum class TermType { PLUS_SIGN, MINUS_SIGN, COEFFICIENT, POWER, UNKNOWN }

fun getTermType(term: String): TermType {
    if (term == "+") return TermType.PLUS_SIGN
    if (term == "-") return TermType.MINUS_SIGN
    if ("^-?[0-9]+$".toRegex().matches(term)) return TermType.COEFFICIENT
    if ("^[a-z](\\^[0-9]+)?$".toRegex().matches(term)) return TermType.POWER

    return TermType.UNKNOWN
}

fun parseMonomial(terms: MutableList<String>): Monomial {
    val sign =
        if (getTermType(terms[0]) == TermType.PLUS_SIGN || getTermType(terms[0]) == TermType.MINUS_SIGN)
            if (getTermType(terms.removeAt(0)) == TermType.PLUS_SIGN) 1.0 else -1.0
        else
            1.0

    val coefficient =
        if (getTermType(terms[0]) == TermType.COEFFICIENT)
            terms.removeAt(0).toDouble()
        else
            1.0

    val powers = mutableMapOf<Char, UInt>()
    while (terms.size != 0 && getTermType(terms[0]) == TermType.POWER) {
        val term = terms.removeAt(0)
        if (term.contains("^")) {
            val splited = term.split("^")
            powers[splited[0].first()] = splited[1].toUInt()
        } else {
            powers[term.first()] = 1u
        }

    }

    return monomialOf(sign * coefficient, powers)
}

fun parsePolynomial(input: String): Result<Polynomial> {
    val terms =
        input
            .trim()
            .replace(" +".toRegex(), " ")
            .split(" ")
            .toMutableList()

    var polynomial = polynomialOf(listOf())

    while (terms.size != 0) {
        val prevTermsSize = terms.size
        val monomial = parseMonomial(terms)
        if (prevTermsSize == terms.size)
            return Result.failure(Exception())

        polynomial = polynomialOf(polynomial.monomials + listOf(monomial))
    }

    return Result.success(polynomial)
}
