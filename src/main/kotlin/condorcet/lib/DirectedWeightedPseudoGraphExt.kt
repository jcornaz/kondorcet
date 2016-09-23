package condorcet.lib

import kraft.DirectedGraph
import kraft.DirectedWeightedPseudoGraph

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

/**
 * Remove all the vertices of the graph to create an list of set ordered from the winners to the losers
 */
fun <T> DirectedGraph<T>.consumeResult(): List<Set<T>> {

    var losers = kotlin.collections.emptyList<Set<T>>()
    var candidates = vertices.filter { degreeFrom(it) == 0 }
    while (vertices.isNotEmpty() && candidates.isNotEmpty()) {
        removeVertices(candidates)
        losers = kotlin.collections.listOf(candidates.toSet()) + losers
        candidates = vertices.filter { degreeFrom(it) == 0 }
    }

    var winners = kotlin.collections.emptyList<Set<T>>()
    candidates = vertices.filter { degreeTo(it) == 0 }
    while (vertices.isNotEmpty() && candidates.isNotEmpty()) {
        removeVertices(candidates)
        winners += kotlin.collections.listOf(candidates.toSet())
        candidates = vertices.filter { degreeTo(it) == 0 }
    }

    val middleClass = vertices.let { if (it.isEmpty()) kotlin.collections.emptyList() else kotlin.collections.listOf(it) }

    return winners + middleClass + losers
}