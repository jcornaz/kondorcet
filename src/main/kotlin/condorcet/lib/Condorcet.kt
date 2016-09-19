package condorcet.lib

import org.jgrapht.alg.cycle.SzwarcfiterLauerSimpleCycles
import org.jgrapht.graph.DefaultWeightedEdge
import org.jgrapht.graph.DirectedWeightedPseudograph
import java.util.*

class Condorcet<T : Any> : Poll<T> {

    private val ballots = HashMap<Ballot<T>, Int>()

    override fun vote(ballot: Ballot<T>, count: Int) {
        ballots[ballot] = (ballots[ballot] ?: 0) + count
    }

    override fun result(): Ballot<T> {
        val graph = DirectedWeightedPseudograph<T, DefaultWeightedEdge>(DefaultWeightedEdge::class.java)

        for ((ballot, count) in ballots)
            graph.add(ballot, count)

        graph.simplify()

        graph.removeCycles()

        var result = emptyList<Set<T>>()
        var winners: Set<T> = graph.vertexSet()
        while (winners.isNotEmpty()) {

            val losers: Set<T> = winners.filter { graph.outDegreeOf(it) == 0 }.toSet()
            losers.forEach { graph.removeVertex(it) }
            winners -= losers

            result = listOf(losers) + result
        }

        return Ballot(result)
    }

    fun DirectedWeightedPseudograph<T, DefaultWeightedEdge>.add(ballot: Ballot<T>, count: Int = 1) {
        var winners = emptySet<T>()

        for (stage in ballot.orderedCandidates) {

            val concreteStage = stage.filterNot { it in winners }

            concreteStage.forEach { addVertex(it) }

            for (winner in winners) {
                for (loser in concreteStage) {

                    val edge =
                            if (!containsEdge(winner, loser)) addEdge(winner, loser)
                            else getEdge(winner, loser)

                    setEdgeWeight(edge, getEdgeWeight(edge) + count)
                }
            }

            winners += concreteStage
        }
    }

    fun DirectedWeightedPseudograph<T, DefaultWeightedEdge>.simplify() {
        val edges = HashSet<Set<T>>()

        for (edge in edgeSet().toSet()) {
            val source = getEdgeSource(edge)
            val target = getEdgeTarget(edge)

            if (!edges.add(setOf(source, target))) {
                val twin = getEdge(target, source)

                val thisWeight = getEdgeWeight(edge)
                val otherWeight = getEdgeWeight(twin)

                val result = thisWeight - otherWeight
                if (result < 0) {
                    removeEdge(edge)
                    setEdgeWeight(twin, -result)
                } else if (result > 0) {
                    removeEdge(twin)
                    setEdgeWeight(edge, result)
                } else {
                    removeEdge(edge)
                    removeEdge(twin)
                }
            }
        }
    }

    fun DirectedWeightedPseudograph<T, DefaultWeightedEdge>.removeCycles() {
        val finder = SzwarcfiterLauerSimpleCycles(this)
        var cycles = finder.findSimpleCycles()

        while (cycles.isNotEmpty()) {
            val cycle = cycles.first()

            var worseEdge: DefaultWeightedEdge? = null
            var worseWeight = Double.POSITIVE_INFINITY

            var previousVertex = cycle[0]
            for (i in 1..(cycle.size - 1)) {
                val currentVertex = cycle[i]

                val edge = getEdge(previousVertex, currentVertex)
                val weight = getEdgeWeight(edge)

                if (weight < worseWeight) {
                    worseEdge = edge
                    worseWeight = weight
                }

                previousVertex = cycle[i]
            }

            removeEdge(worseEdge)

            cycles = finder.findSimpleCycles()
        }
    }
}