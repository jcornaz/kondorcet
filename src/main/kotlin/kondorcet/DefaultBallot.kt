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

fun <T : Any> ballot(vararg candidates: T): Ballot<T> = DefaultBallot.of(*candidates)
fun <T : Any> ballot(vararg candidates: Collection<T>): Ballot<T> = DefaultBallot.of(*candidates)
fun <T : Any> emptyBallot(): Ballot<T> = DefaultBallot.blank<T>()

operator fun <T : Any> Ballot<T>.plus(ballot: Ballot<T>): Ballot<T> =
        DefaultBallot(orderedCandidates + ballot.orderedCandidates)

operator fun <T : Any> Ballot<T>.plus(candidates: Collection<T>): Ballot<T> =
        if (candidates.isEmpty()) this
        else DefaultBallot(orderedCandidates.plus<Set<T>>(candidates.toSet()))

operator fun <T : Any> Ballot<T>.plus(candidate: T): Ballot<T> = plus(setOf(candidate))

infix fun <T : Any> Ballot<T>.with(candidates: Collection<T>) =
        candidates().let { c -> candidates.filterNot { it in c } }.let {
            if (it.isEmpty()) this
            else this + it
        }