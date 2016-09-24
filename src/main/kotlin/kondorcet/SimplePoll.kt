package kondorcet

import java.util.*

/**
 * An implementation of [Poll] that does not do anything more than store ballots
 */
class SimplePoll<T : Any> : Poll<T> {

    private val map = HashMap<Ballot<T>, Int>()

    override val ballots: Map<Ballot<T>, Int>
        get() = map

    override fun vote(ballot: Ballot<T>, count: Int) {
        map[ballot] = (map[ballot] ?: 0) + count
    }
}