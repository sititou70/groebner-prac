package types

data class Monomial(
    val coefficient: Double,
    val powers: Map<Char, UInt>
)

fun monomialOf(
    coefficient: Double,
    powers: Map<Char, UInt>
): Monomial {
    return Monomial(
        coefficient,
        powers.entries.filter { it.value != 0u }.associate { it.key to it.value }
    )
}

