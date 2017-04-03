package kondorcet

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