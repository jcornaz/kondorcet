package kondorcet

/**
 * A poll receive ballots and provide a resultOf
 */
interface Poll<T : Any> {

    /**
     * Set of ballots in the poll
     */
    val ballots: Map<Ballot<T>, Int>

    /**
     * Add a ballot to the pool
     *
     * @param ballot Ballot to add
     * @param count How many of the the ballot should be counted
     */
    fun vote(ballot: Ballot<T>, count: Int = 1)
}