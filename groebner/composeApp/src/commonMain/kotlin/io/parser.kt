package io

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
            if (getTermType(terms.removeAt(0)) == TermType.PLUS_SIGN) 1 else -1
        else
            1

    val coefficient =
        if (getTermType(terms[0]) == TermType.COEFFICIENT)
            terms.removeAt(0).toLong()
        else
            1

    val powers = HashMap<Char, Int>()
    while (terms.size != 0 && getTermType(terms[0]) == TermType.POWER) {
        val term = terms.removeAt(0)
        if (term.contains("^")) {
            val splited = term.split("^")
            powers[splited[0].first()] = splited[1].toInt()
        } else {
            powers[term.first()] = 1
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

    val monos = mutableListOf<Monomial>()

    while (terms.size != 0) {
        val prevTermsSize = terms.size
        val mono = parseMonomial(terms)
        if (prevTermsSize == terms.size)
            return Result.failure(Exception())

        monos.add(mono)
    }

    return Result.success(Polynomial(monos))
}
