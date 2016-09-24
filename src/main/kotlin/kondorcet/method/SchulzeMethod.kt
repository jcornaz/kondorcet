package kondorcet.method

import kondorcet.method.SchulzeMethod.result
import kondorcet.method.SchulzeMethod.resultOf
import kraft.DirectedPseudoGraph
import kraft.DirectedWeightedPseudoGraph
import kraft.algo.widestPaths

/**
 * Schulze method poll. It ensure to get a single winner and this winner will be a condorcet winner if possible.
 *
 * This method provide a sorted pool of winner
 *
 * The [resultOf] method complexity is *O(n^3 + b)* for *n* candidates and *b* ballots
 */
object SchulzeMethod : GraphBasedMethod() {

    override fun <T : Any> result(graph: DirectedWeightedPseudoGraph<T, Int>): DirectedPseudoGraph<T> =
            graph.widestPaths(0).apply { simplify() }
}