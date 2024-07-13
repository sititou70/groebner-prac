import arithmetic.*
import types.Monomial
import types.Polynomial
import types.monomialOf
import types.polynomialOf
import kotlin.math.max

fun sPolynomial(p1: Polynomial, p2: Polynomial): Polynomial {
    val leastCommonMultiple =
        monomialOf(
            1.0,
            (p1.monomials[0].powers.keys + p2.monomials[0].powers.keys)
                .associateWith {
                    max(
                        p1.monomials[0].powers[it] ?: 0u,
                        p2.monomials[0].powers[it] ?: 0u
                    )
                }
        )

    return subPolynomial(
        mulPolynomial(
            polynomialOf(
                listOf(
                    divMonomial(
                        leastCommonMultiple,
                        p1.monomials[0]
                    ).getOrThrow()
                )
            ),
            p1
        ),
        mulPolynomial(
            polynomialOf(
                listOf(
                    divMonomial(
                        leastCommonMultiple,
                        p2.monomials[0]
                    ).getOrThrow(),
                )
            ),
            p2
        )

    )
}

fun inMonomialIdeal(m: Monomial, basis: Set<Monomial>): Boolean {
    for (base in basis) {
        if (divMonomial(m, base).isSuccess)
            return true
    }

    return false
}

fun reduceGroebnerBasis(inputBasis: Set<Polynomial>): Set<Polynomial> {
    fun isRemovableBase(base: Polynomial, basis: Set<Polynomial>): Boolean {
        val basisWithoutBase = basis.toMutableSet()
        basisWithoutBase.remove(base)

        for (monomial in base.monomials) {
            if (inMonomialIdeal(
                    monomial,
                    basisWithoutBase.map { it.monomials[0] }.toSet()
                )
            )
                return true
        }

        return false
    }

    val basis = inputBasis.map {
        mulPolynomialByMonomial(
            it,
            monomialOf(1 / it.monomials[0].coefficient, mapOf())
        )
    }.toMutableSet()

    var removed = true
    while (removed && basis.isNotEmpty()) {
        removed = false

        for (base in basis) {
            if (isRemovableBase(base, basis)) {
                basis.remove(base)
                val remainder = reducePolynomial(base, basis.toList()).second
                if (remainder.monomials.isNotEmpty())
                    basis.add(remainder)
                removed = true
                break
            }
        }
    }

    return basis
}

fun groebnerBasis(inputBasis: Set<Polynomial>): Set<Polynomial> {
    var basis = inputBasis.toMutableSet()
    var isChanged: Boolean
    do {
        isChanged = false

        val currentBasis = basis.toList()
        for (b1 in currentBasis) {
            for (b2 in currentBasis) {
                if (b1 == b2) continue

                val remainder = reducePolynomial(sPolynomial(b1, b2), currentBasis).second
                if (remainder.monomials.isNotEmpty()) {
                    basis.add(remainder)
                    isChanged = true
                }
            }
        }

    } while (isChanged)

    return reduceGroebnerBasis(basis)
}
