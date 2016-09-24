package kondorcet

import java.util.*

interface Ballot<out T : Any> {
    val orderedCandidates: List<Set<T>>

    val winners: Set<T>
        get() = orderedCandidates.firstOrNull() ?: emptySet()

    val winner: T?
        get() = winners.let { if (it.size == 1) it.first() else null }

    fun candidates(): Set<T> =
            orderedCandidates.fold(emptySet<T>()) { set, elt -> set + elt }

    fun hasDuplicates(): Boolean {
        val set = HashSet<T>()

        for (stage in orderedCandidates)
            for (candidate in stage)
                if (!set.add(candidate)) return true

        return false
    }
}