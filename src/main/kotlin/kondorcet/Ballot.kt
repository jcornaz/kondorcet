package kondorcet

import java.util.*

/**
 * Represent a ballot
 */
interface Ballot<out T : Any> {

    /**
     * Ordered list of candidates
     */
    val orderedCandidates: List<Set<T>>

    /**
     * List of winner (many candidates mean ex aequo)
     */
    val winners: Set<T>
        get() = orderedCandidates.firstOrNull() ?: emptySet()

    /**
     * Single winner (if ther is one)
     */
    val winner: T?
        get() = winners.let { if (it.size == 1) it.first() else null }

    /**
     * Set containing all the candidates
     */
    fun candidates(): Set<T> =
            orderedCandidates.fold(emptySet<T>()) { set, elt -> set + elt }

    /**
     * Return true if, and only if, the ballot contains at least one candidate twice
     */
    fun hasDuplicates(): Boolean {
        val set = HashSet<T>()

        for (stage in orderedCandidates)
            for (candidate in stage)
                if (!set.add(candidate)) return true

        return false
    }
}