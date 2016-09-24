package kondorcet.method

import kondorcet.*
import kraft.DirectedGraph
import kraft.DirectedPseudoGraph
import kraft.DirectedWeightedPseudoGraph
import kraft.graph.pseudo.DefaultDirectedWeightedPseudoGraph

/**
 * Abstraction of [VoteMethod] that are based on graphs
 */
abstract class GraphBasedMethod : VoteMethod {

    override fun <T : Any> resultOf(poll: Poll<T>): Ballot<T> =
            DefaultDirectedWeightedPseudoGraph<T, Int>().apply {
                for ((ballot, count) in poll.ballots)
                    add(ballot, count)
            }.let { result(it) }.consumeResult()

    /**
     * Compute the resultOf graph from the data graph
     *
     * @param graph A graph where vertices are candidates and any edge from x to y of weight w represent w victories of x against y.
     * @return A graph where every vertices are candidates and any edge from x to y represent the victory of x against y.
     */
    abstract fun <T : Any> result(graph: DirectedWeightedPseudoGraph<T, Int>): DirectedPseudoGraph<T>

    /**
     * Add a ballot the the graph.
     *
     * This method create or update edges from the winners to the losers with weight representing how many times the winners won.
     *
     * @param ballot Ballot to add
     * @param count How many times to add the ballot
     */
    fun <T : Any> DirectedWeightedPseudoGraph<T, Int>.add(ballot: Ballot<T>, count: Int = 1) {
        var winners = emptySet<T>()

        for (stage in ballot.orderedCandidates) {

            val concreteStage = stage.filterNot { it in winners }

            addVertices(concreteStage)

            for (winner in winners)
                for (loser in concreteStage)
                    this[winner, loser] = (this[winner, loser] ?: 0) + count

            winners += concreteStage
        }
    }

    /**
     * Remove all the vertices of the graph to create an list of set ordered from the winners to the losers
     */
    fun <T : Any> DirectedGraph<T>.consumeResult(): Ballot<T> {

        var losers = emptyBallot<T>()
        var candidates = vertices.filter { degreeFrom(it) == 0 }
        while (vertices.isNotEmpty() && candidates.isNotEmpty()) {
            removeVertices(candidates)
            losers = ballot(candidates) + losers
            candidates = vertices.filter { degreeFrom(it) == 0 }
        }

        var winners = emptyBallot<T>()
        candidates = vertices.filter { degreeTo(it) == 0 }
        while (vertices.isNotEmpty() && candidates.isNotEmpty()) {
            removeVertices(candidates)
            winners += ballot(candidates)
            candidates = vertices.filter { degreeTo(it) == 0 }
        }

        val middleClass = vertices.let { if (it.isEmpty()) emptyBallot() else ballot(it) }

        return winners + middleClass + losers
    }

    /**
     * Simplify a graph.
     *
     * For each edge who ave a *twin* (an edge for the same vertices but in the opposite direction), it will
     * remove the unnecessary edge and update the weight to represent the differences between the edges.
     */
    fun <T> DirectedWeightedPseudoGraph<T, Int>.simplify() {
        for ((i, j) in edges) {
            val a = this[i, j]
            val b = this[j, i]

            if (a != null && b != null) {
                if (a > b) {
                    this[i, j] = a - b
                    this[j, i] = null
                } else if (b > a) {
                    this[j, i] = b - a
                    this[i, j] = null
                } else {
                    this[i, j] = null
                    this[j, i] = null
                }
            }
        }
    }
}