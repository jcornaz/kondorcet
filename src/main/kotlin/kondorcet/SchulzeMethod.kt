package kondorcet

import kraft.algo.widestPaths
import kraft.graph.pseudo.DefaultDirectedWeightedPseudoGraph
import java.util.*

/**
 * Schulze method poll. It ensure to get a single winner and this winner will be a condorcet winner if possible.
 *
 * This method provide a sorted pool of winner
 *
 * The [result] method complexity is *O(n^3 + b)* for *n* candidates and *b* ballots
 */
class SchulzeMethod<T : Any> : Poll<T> {

    private val ballots = HashMap<Ballot<T>, Int>()

    override fun vote(ballot: Ballot<T>, count: Int) {
        ballots[ballot] = (ballots[ballot] ?: 0) + count
    }

    override fun result(): Ballot<T> {

        val result = DefaultDirectedWeightedPseudoGraph<T, Int>().let {
            for ((ballot, count) in ballots)
                it.add(ballot, count)

            it.widestPaths(0)
        }.apply { simplify() }.consumeResult()

        return Ballot(result)
    }
}