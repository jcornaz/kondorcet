package kondorcet

fun <T : Any> ballot(vararg candidates: T): Ballot<T> = DefaultBallot.of(*candidates)
fun <T : Any> ballot(vararg candidates: Collection<T>): Ballot<T> = DefaultBallot.of(*candidates)
fun <T : Any> emptyBallot(): Ballot<T> = DefaultBallot.blank<T>()

operator fun <T : Any> Ballot<T>.plus(ballot: Ballot<T>): Ballot<T> =
        ballot.orderedCandidates.fold(this) { ballot, set -> ballot + set }

operator fun <T : Any> Ballot<T>.plus(candidates: Collection<T>): Ballot<T> =
        if (candidates.isEmpty()) this
        else DefaultBallot(orderedCandidates + listOf(candidates.toSet()))

operator fun <T : Any> Ballot<T>.plus(candidate: T): Ballot<T> = plus(setOf(candidate))