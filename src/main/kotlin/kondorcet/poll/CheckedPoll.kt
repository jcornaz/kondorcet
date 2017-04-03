package kondorcet.poll

import kondorcet.Ballot
import kondorcet.Poll
import java.util.*

/**
 * An implementation of [Poll] that check that ballots does not have duplicate before to take it
 */
class CheckedPoll<T : Any> : Poll<T> {

    private val map = HashMap<Ballot<T>, Int>()

    override val ballots: Map<Ballot<T>, Int>
        get() = map

    override fun vote(ballot: Ballot<T>, count: Int) {
        if (ballot.hasDuplicates()) throw IllegalArgumentException("The ballot contains diplicats")
        map[ballot] = (map[ballot] ?: 0) + count
    }
}