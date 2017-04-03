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

    /** Returns a ballots representing the graph (as a victory graph) */
    internal fun <T : Any> Graph<T, Any>.toBallot(): Ballot<T> {

        val (graph, winners) = extractCandidates(vertices, { getDegreeTo(it) == 0 && getDegreeFrom(it) > 0 }) { list, set -> list + listOf(set) }
        val (_, losers) = graph.extractCandidates(vertices - winners.flatten(), { getDegreeFrom(it) == 0 }) { list, set -> listOf(set) + list }
        val others = (vertices - winners.flatten() - losers.flatten()).let { if (it.isEmpty()) emptyList() else listOf(it.toSet()) }

        return ballot(winners + others + losers)
    }

    /**
     * Extract candidates from the graph
     *
     * @param candidates Candidates to try to extract
     * @param selectCandidate Method to select extractable candidates
     * @return a pair with : the graph without the extracted candidates and the extracted candidates
     */
    internal fun <T : Any, W : Any> Graph<T, W>.extractCandidates(
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