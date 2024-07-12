package io

import lexicographicOrder
import types.Monomial
import types.Polynomial

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

    val powers = HashMap<Char, UInt>()
    while (terms.size != 0 && getTermType(terms[0]) == TermType.POWER) {
        val term = terms.removeAt(0)
        if (term.contains("^")) {
            val splited = term.split("^")
            powers[splited[0].first()] = splited[1].toUInt()
        } else {
            powers[term.first()] = 1u
        }

    }

    return Monomial(sign * coefficient, powers)
}

fun parsePolynomial(input: String): Result<Polynomial> {
    val terms =
        input
            .trim()
            .replace(" +".toRegex(), " ")
            .split(" ")
            .toMutableList()

    val monomials = mutableListOf<Monomial>()

    while (terms.size != 0) {
        val prevTermsSize = terms.size
        val mono = parseMonomial(terms)
        if (prevTermsSize == terms.size)
            return Result.failure(Exception())

        monomials.add(mono)
    }

    val sortedMonomials = monomials.sortedWith { a, b ->
        lexicographicOrder(a, b)
    }.toMutableList()


    return Result.success(Polynomial(sortedMonomials))
}
