package kondorcet

import kondorcet.method.SchulzeMethod

/**
 * Represent a an algorithm used to compute the winners and losers from a set of ballots.
 */
interface VoteMethod {

    /**
     * Compute the resultOf of a poll
     *
     * @param poll Poll of ballots
     * @return A ballot that represent the better the poll
     */
    fun <T : Any> resultOf(poll: Poll<T>): Ballot<T>
}

/**
 * Compute the result of a poll
 *
 * @param method Vote method to use
 * @return A ballot that represent the better the poll
 */
fun <T : Any> Poll<T>.result(method: VoteMethod = SchulzeMethod) = method.resultOf(this)