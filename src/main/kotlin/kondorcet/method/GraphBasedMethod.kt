package kondorcet.method

import kable.filterValues
import kable.mapValues
import kondorcet.Ballot
import kondorcet.Poll
import kondorcet.VoteMethod
import kondorcet.graph.*
import kondorcet.utils.orZero

/**
 * Abstraction of [VoteMethod] that are based on graphs
 */
abstract class GraphBasedMethod : VoteMethod {

    override fun <T : Any> resultOf(poll: Poll<T>): Ballot<T> {
        val candidates = poll.candidates

        var graph: Graph<T, Int> = SimpleGraph(candidates)
        var abstentions = emptyMap<Set<T>, Int>()

        for ((ballot, count) in poll.ballots) {
            graph += ballot to count
            abstentions += (candidates - ballot.candidates)
                    .flatMap { c1 -> (candidates - c1).map { c2 -> setOf(c1, c2) } }
                    .map { it to (abstentions[it].orZero() + count) }
        }

        return graph.edges
                .mapValues { (source, target, weight) ->
                    weight - graph[target, source].orZero() - abstentions[setOf(target, source)].orZero()
                }
                .filterValues { it > 0 }
                .let { ballotOf(SimpleGraph(candidates, it)) }
    }

    internal operator fun <T : Any> Graph<T, Int>.plus(ballots: Pair<Ballot<T>, Int>): Graph<T, Int> {
        val (ballot, count) = ballots
        var result = this
        var losers = ballot.orderedCandidates

        while (losers.isNotEmpty()) {
            val winnerSet = losers.first()
            losers = losers.subList(1, losers.size)

            for (loser in losers.flatten()) {
                for (winner in winnerSet)
                    result += Edge(winner, loser, result[winner, loser].orZero() + count)
            }
        }

        return result
    }

    /**
     * Returns a simplified version of the graph, without edge twins.
     *
     * In case of twin the two edges are replaced by one edge representing the difference, and pointing in the positive direction.
     *
     * In case of equality of the twins, the edges are simply removed.
     */
    internal fun <T : Any> Graph<T, Int>.simplify(): Graph<T, Int> =
            edges
                    .mapValues { (source, target, weight) -> weight - this[target, source].orZero() }
                    .filterValues { it > 0 }
                    .let { SimpleGraph(vertices, it) }

    /**
     * Compute the result graph from the data graph
     *
     * @receiver A graph adjacency matrix where vertices are candidates and any edge from x to y of weight w represent w victories of x against y.
     * @return A graph adjacency matrix where every vertices are candidates and any edge from x to y represent the victory state of x against y (true = victory, false = defeat)
     */
    internal abstract fun <T : Any> ballotOf(graph: Graph<T, Int>): Ballot<T>
}
