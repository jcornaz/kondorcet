package kondorcet.method

import kondorcet.*
import java.util.*

/**
 * The relative majority count the winners of each ballots and don't care about the others.
 *
 * The result is ordered by the quantity of ballots for each candidates
 */
object RelativeMajorityMethod : VoteMethod {

    override fun <T : Any> resultOf(poll: Poll<T>): Ballot<T> {
        val list = HashMap<T, Int>().apply {
            for ((ballot, count) in poll.ballots)
                ballot.winners.forEach { this[it] = (this[it] ?: 0) + count }
        }.toList().sortedBy { -it.second }

        println(list)

        var result = emptyBallot<T>()
        var candidates = emptySet<T>()
        var currentScore: Int? = null
        for ((candidate, score) in list) {

            if (score != currentScore) {
                result += candidates
                candidates = emptySet()
                currentScore = score
            }

            candidates += candidate
        }

        result += candidates

        return result
    }
}