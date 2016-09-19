package condorcet.lib

interface Poll<T : Any> {
    fun vote(ballot: Ballot<T>, count: Int = 1)
    fun result(): Ballot<T>
}