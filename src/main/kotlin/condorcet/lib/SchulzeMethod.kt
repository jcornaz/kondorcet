package condorcet.lib

import kraft.algo.widestPaths
import kraft.graph.pseudo.DefaultDirectedWeightedPseudoGraph
import java.util.*

class SchulzeMethod<T : Any> : Poll<T> {

    private val ballots = HashMap<Ballot<T>, Int>()

    override fun vote(ballot: Ballot<T>, count: Int) {
        ballots[ballot] = (ballots[ballot] ?: 0) + count
    }

    override fun result(): Ballot<T> {

        val graph = DefaultDirectedWeightedPseudoGraph<T, Int>().let {
            for ((ballot, count) in ballots)
                it.add(ballot, count)

            it.widestPaths(0)
        }.apply { simplify() }


        var losers = emptyList<Set<T>>()
        var candidates = graph.vertices.filter { graph.degreeFrom(it) == 0 }
        while (graph.vertices.isNotEmpty() && candidates.isNotEmpty()) {
            graph.removeVertices(candidates)
            losers = listOf(candidates.toSet()) + losers
            candidates = graph.vertices.filter { graph.degreeFrom(it) == 0 }
        }

        var winners = emptyList<Set<T>>()
        candidates = graph.vertices.filter { graph.degreeTo(it) == 0 }
        while (graph.vertices.isNotEmpty() && candidates.isNotEmpty()) {
            graph.removeVertices(candidates)
            winners += listOf(candidates.toSet())
            candidates = graph.vertices.filter { graph.degreeTo(it) == 0 }
        }

        return Ballot(winners + graph.vertices.let { if (it.isEmpty()) emptyList() else listOf(it) } + losers)
    }
}