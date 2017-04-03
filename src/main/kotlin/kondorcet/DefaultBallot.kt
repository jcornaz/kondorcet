package kondorcet

/**
 * Vote ballot
 *
 * Contains all the candidates ordered by preferences.
 *
 * @property orderedCandidates Each element of this list is a set of candidate who are ex aequo.
 */
class DefaultBallot<out T : Any>(orderedCandidates: List<Set<T>> = emptyList()) : Ballot<T> {

    override val orderedCandidates: List<Set<T>> = orderedCandidates.filterNot(Set<T>::isEmpty)

    constructor(vararg candidates: T) : this(candidates.map { setOf(it) })
    constructor(vararg candidates: Collection<T>) : this(candidates.map { it.toSet() })

    override fun equals(other: Any?) = other is Ballot<*> && other.orderedCandidates == orderedCandidates

    override fun hashCode() = orderedCandidates.hashCode()

    override fun toString() = "DefaultBallot(orderedCandidates=$orderedCandidates)"
}