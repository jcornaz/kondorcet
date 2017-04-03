@file:JvmName("Ballots")

package kondorcet.model

import kondorcet.Ballot


/** Create a new ballot with the specified ordered candidates */
fun <T : Any> ballot(vararg candidates: T): Ballot<T> = DefaultBallot(*candidates)

/** Create a new ballot with the specified ordered candidates */
fun <T : Any> ballot(vararg candidates: Collection<T>): Ballot<T> = DefaultBallot(*candidates)

/** Create a new ballot with the specified ordered candidates */
fun <T : Any> ballot(candidates: List<Set<T>>): Ballot<T> = DefaultBallot(candidates)

/** Create a new empty ballot */
fun <T : Any> emptyBallot(): Ballot<T> = DefaultBallot()

/**
 * Create a ballot that have at least all the specified candidates
 *
 * The candidates that wasn't in the original ballot are added as losers
 */
infix fun <T : Any> Ballot<T>.with(candidates: Collection<T>) =
        candidates.filterNot { it in this.candidates }.let {
            if (it.isEmpty()) this else ballot(this.orderedCandidates + listOf(it.toSet()))
        }