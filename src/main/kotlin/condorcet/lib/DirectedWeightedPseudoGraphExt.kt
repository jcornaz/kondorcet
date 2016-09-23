package condorcet.lib

import kraft.DirectedWeightedPseudoGraph

/**
 * Add a ballot the the graph.
 *
 * This method create or update edges from the winners to the loosers with weight representing how many times the winners won.
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