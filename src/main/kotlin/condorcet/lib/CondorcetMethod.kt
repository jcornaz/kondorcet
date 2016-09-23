package condorcet.lib

import kraft.graph.pseudo.DefaultDirectedWeightedPseudoGraph
import java.util.*

/**
 * Standard condorcet method poll. It does not ensure to get a single winner, but if it does this guaranteed to be a condorcet winner.
 *
 * The [result] method complexity is *O(n^2 + b)* for *n* candidates and *b* ballots
 */
class CondorcetMethod<T : Any> : Poll<T> {

    private val ballots = HashMap<Ballot<T>, Int>()

    override fun vote(ballot: Ballot<T>, count: Int) {
        ballots[ballot] = (ballots[ballot] ?: 0) + count
    }

    override fun result(): Ballot<T> {
        val graph = DefaultDirectedWeightedPseudoGraph<T, Int>().apply {
            for ((ballot, count) in ballots)
                add(ballot, count)

            simplify()
        }

        val winners = graph.vertices.filter { graph.degreeFrom(it) > 0 && graph.degreeTo(it) == 0 }.toSet()
        val losers = graph.vertices - winners

        var result = emptyList<Set<T>>()

        if (winners.isNotEmpty())
            result += listOf(winners)

        if (losers.isNotEmpty())
            result += listOf(losers)

        return Ballot(result)
    }
}