package kondorcet

/**
 * Represent a vote ballot
 *
 * A ballot contains candidates ordered by preferences.
 *
 * A ballot cannot contains more that once each candidate
 */
interface Ballot<out T : Any> {

    /** Ordered list of candidates */
    val orderedCandidates: List<Set<T>>

    /** Candidates */
    val candidates: Set<T>
        get() = orderedCandidates.flatten().toSet()

    /**
     * List of winner (many candidates means ex aequo)
     */
    val winners: Set<T>
        get() = orderedCandidates.firstOrNull() ?: emptySet()

    /**
     * Single winner (if there is one)
     */
    val winner: T?
        get() = winners.let { if (it.size == 1) it.first() else null }
}