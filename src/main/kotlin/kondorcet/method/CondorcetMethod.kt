package kondorcet.method

import kable.Table
import kable.associateTable
import kable.entry
import kondorcet.method.CondorcetMethod.resultOf
import kondorcet.orZero

/**
 * Standard condorcet method poll. It does not ensure to get a single winner, but if it does this guaranteed to be a condorcet winner.
 *
 * The [resultOf] method complexity is *O(n^2 + b)* for *n* candidates and *b* ballots
 */
object CondorcetMethod : GraphBasedMethod() {

    override fun <T : Any> victoriesTableOf(graph: Table<T, T, Int>): Table<T, T, Boolean> =
            (graph.rows + graph.columns).let { candidates ->
                candidates.flatMap { c1 ->
                    candidates.filter { it != c1 }.map { c1 to it }
                }.associateTable { (c1, c2) ->
                    entry(c1, c2, graph[c1, c2].orZero() > graph[c2, c1].orZero())
                }
            }
}