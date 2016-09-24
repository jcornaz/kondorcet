package kondorcet

/**
 * Vote ballot
 *
 * Contains all the candidates ordered by preferences.
 *
 * @property orderedCandidates Each element of this list is a set of candidate who are ex aequo.
 */
data class DefaultBallot<out T : Any>(override val orderedCandidates: List<Set<T>> = emptyList()) : Ballot<T> {

    companion object {
        fun <T : Any> blank() = DefaultBallot<T>(emptyList())
        fun <T : Any> of(vararg candidates: T) = DefaultBallot(candidates.map { setOf(it) })
        fun <T : Any> of(vararg candidates: Collection<T>) = DefaultBallot(candidates.map { it.toSet() })
    }
}