package kondorcet.method

import kondorcet.Ballot
import kondorcet.ballot
import kondorcet.graph.Graph
import kondorcet.graph.minus
import kondorcet.method.CondorcetMethod.resultOf

/**
 * Standard condorcet method poll. It does not ensure to get a single winner, but if it does this guaranteed to be a condorcet winner.
 *
 * The [resultOf] method complexity is *O(n^2 + b)* for *n* candidates and *b* ballots
 */
object CondorcetMethod : GraphBasedMethod() {

    override fun <T : Any> ballotOf(graph: Graph<T, Int>) =
            graph.toBallot()

    fun <T : Any> Graph<T, Any>.toBallot(): Ballot<T> {

        val (graph, winners) = extractCandidates(vertices, { getDegreeTo(it) == 0 && getDegreeFrom(it) > 0 }) { list, set -> list + listOf(set) }
        val (_, losers) = graph.extractCandidates(vertices - winners.flatten(), { getDegreeFrom(it) == 0 }) { list, set -> listOf(set) + list }
        val others = (vertices - winners.flatten() - losers.flatten()).let { if (it.isEmpty()) emptyList() else listOf(it.toSet()) }

        return ballot(winners + others + losers)
    }

    fun <T : Any, W : Any> Graph<T, W>.extractCandidates(
            candidates: Collection<T>,
            selectCandidate: Graph<T, Any>.(candidate: T) -> Boolean,
            merge: (List<Set<T>>, Set<T>) -> List<Set<T>>
    ): Pair<Graph<T, W>, List<Set<T>>> {

        var candidatesPool = candidates
        var graph = this
        var result = emptyList<Set<T>>()

        var nextCandidates = candidatesPool.filter { graph.selectCandidate(it) }
        while (candidatesPool.isNotEmpty() && nextCandidates.isNotEmpty()) {

            for (candidate in nextCandidates)
                graph -= candidate

            result = merge(result, nextCandidates.toSet())
            candidatesPool -= nextCandidates
            nextCandidates = candidatesPool.filter { graph.selectCandidate(it) }
        }

        return graph to result
    }
}