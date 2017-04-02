package kondorcet

/**
 * Represent a ballot
 */
interface Ballot<out T : Any> {

    /**
     * Ordered list of candidates
     */
    val orderedCandidates: List<Set<T>>

    /** Candidates */
    val candidates: Set<T>
        get() = orderedCandidates.flatten().toSet()

    /**
     * List of winner (many candidates mean ex aequo)
     */
    val winners: Set<T>
        get() = orderedCandidates.firstOrNull() ?: emptySet()

    /**
     * Single winner (if there is one)
     */
    val winner: T?
        get() = winners.let { if (it.size == 1) it.first() else null }

    /**
     * Return true if, and only if, the ballot contains at least one candidate twice
     */
    fun hasDuplicates() = candidates.size != orderedCandidates.flatten().size
}