package kondorcet

/**
 * A poll receive ballots and provide a result
 */
interface Poll<T : Any> {

    /**
     * Add a ballot to the pool
     *
     * @param ballot Ballot to add
     * @param count How many of the the ballot should be counted
     */
    fun vote(ballot: Ballot<T>, count: Int = 1)

    /**
     * Compute the result
     *
     * @return A ballot which is the most representative of the votes.
     */
    fun result(): Ballot<T>
}