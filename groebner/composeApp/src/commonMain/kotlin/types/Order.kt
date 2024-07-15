package types

fun getVariableOrder(
    variable: Char,
    power: Map<Char, UInt>,
): UInt {
    val order = power[variable]
    return order ?: 0u
}

/**
 * 単項の辞書式順序
 *
 * 戻り値:
 *
 * - 1: bが大きい
 * - 0: aとbは同じ
 * - -1: aが大きい
 */
fun lexicographicOrder(
    a: Monomial,
    b: Monomial,
): Int {
    val variables =
        (a.powers.keys.toSet() union b.powers.keys.toSet())
            .toList()
            .sorted()

    for (variable in variables) {
        val orderA = getVariableOrder(variable, a.powers)
        val orderB = getVariableOrder(variable, b.powers)

        if (orderA < orderB) return 1
        if (orderA > orderB) return -1
    }

    return 0
}
