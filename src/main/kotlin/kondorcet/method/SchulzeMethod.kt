package kondorcet.method

import kondorcet.graph.*
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

    override fun <T : Any> ballotOf(graph: Graph<T, Int>) =
            CondorcetMethod.ballotOf(graph.widestPath().simplify())

    /**
     * Returns the widest paths of the graph with the Floyd Warshall Algorithm
     */
    fun <T : Any> Graph<T, Int>.widestPath(): Graph<T, Int> {
        var result: Graph<T, Int> = SimpleGraph(vertices)

        for ((source, target, weight) in edges) {
            val i = source
            val j = target

            if (i != j) {
                val ij = weight
                val ji = result[j, i].orZero()

                if (ij > ji)
                    result += Edge(i, j, ij)
                else
                    result -= i to j
            }
        }

        for (i in vertices) {
            for (j in (vertices - i)) {
                for (k in (vertices - i - j)) {
                    val weight = max(
                            result[j, k].orZero(),
                            min(
                                    result[j, i].orZero(),
                                    result[i, k].orZero()
                            )
                    )

                    if (weight > 0)
                        result += Edge(j, k, weight)
                    else
                        result -= j to k
                }
            }
        }

        return result
    }
}