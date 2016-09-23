package condorcet.lib

import java.util.*

/**
 * Vote ballot
 *
 * Contains all the candidates ordered by preferences.
 *
 * @property orderedCandidates Each element of this list is a set of candidate who are ex aequo.
 */
data class Ballot<T : Any>(val orderedCandidates: List<Set<T>> = emptyList()) {

    companion object {
        fun <T : Any> blank() = Ballot<T>(emptyList())
        fun <T : Any> of(candidates: List<T>) = Ballot(candidates.map { setOf(it) })
        fun <T : Any> of(vararg candidates: T) = of(candidates.toList())
    }

    val candidates by lazy { orderedCandidates.fold(emptySet<T>()) { set, elt -> set + elt } }

    val hasDuplicates by lazy {
        val set = HashSet<T>()

        for (stage in orderedCandidates)
            for (candidate in stage)
                if (!set.add(candidate)) return@lazy false

        return@lazy true
    }

    operator fun plus(candidates: Set<T>) = Ballot(orderedCandidates.plus<Set<T>>(candidates.toSet()))
    operator fun plus(candidate: T) = plus(setOf(candidate))
}