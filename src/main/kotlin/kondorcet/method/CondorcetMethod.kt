package kondorcet.method

import kondorcet.method.CondorcetMethod.result
import kondorcet.method.CondorcetMethod.resultOf
import kraft.DirectedPseudoGraph
import kraft.DirectedWeightedPseudoGraph

/**
 * Standard condorcet method poll. It does not ensure to get a single winner, but if it does this guaranteed to be a condorcet winner.
 *
 * The [resultOf] method complexity is *O(n^2 + b)* for *n* candidates and *b* ballots
 */
object CondorcetMethod : GraphBasedMethod() {

    override fun <T : Any> result(graph: DirectedWeightedPseudoGraph<T, Int>): DirectedPseudoGraph<T> =
            graph.apply { simplify() }
}