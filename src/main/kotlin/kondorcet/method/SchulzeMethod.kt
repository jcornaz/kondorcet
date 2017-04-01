package kondorcet.method

import kable.*
import kondorcet.max
import kondorcet.method.SchulzeMethod.resultOf
import kondorcet.min
import kondorcet.orZero

/**
 * Schulze method poll. It ensure to get a single winner and this winner will be a condorcet winner if possible.
 *
 * This method provide a sorted pool of winner
 *
 * The [resultOf] method complexity is *O(n^3 + b)* for *n* candidates and *b* ballots
 */
object SchulzeMethod : GraphBasedMethod() {

    override fun <T : Any> victoriesTableOf(graph: Table<T, T, Int>): Table<T, T, Boolean> =
            CondorcetMethod.victoriesTableOf(graph.widestPath())

    fun <T : Any> Table<T, T, Int>.widestPath(): Table<T, T, Int> {
        var result = emptyTable<T, T, Int>()

        for ((source, target, weight) in this) {
            val i = source
            val j = target

            if (i != j) {
                val ij = weight
                val ji = this[j, i].orZero()

                if (ij > ji)
                    result += entry(i, j, ij)
                else
                    result -= i to j
            }
        }

        val vertices = rows + columns
        for (i in vertices) {
            for (j in vertices) {
                if (i != j) {
                    for (k in vertices) {
                        if (i != k && j != k) {
                            result += entry(j, k,
                                    max(
                                            result[j, k].orZero(),
                                            min(
                                                    result[j, i].orZero(),
                                                    result[i, k].orZero()
                                            )
                                    )
                            )
                        }
                    }
                }
            }
        }

        return result
    }
}