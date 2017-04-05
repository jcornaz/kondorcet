@file:JvmName("Polls")

package kondorcet.model

import kondorcet.Ballot
import kondorcet.Poll
import kondorcet.VoteMethod
import kondorcet.method.SchulzeMethod
import kondorcet.utils.orZero

/** Create a new empty poll */
fun <T : Any> emptyPoll(): Poll<T> = DefaultPoll()

/** Create a new poll with the given ballot map */
fun <T : Any> pollOf(ballots: Map<Ballot<T>, Int>): Poll<T> = DefaultPoll(ballots)

/** Create a new poll with the given ballot map */
fun <T : Any> pollOf(vararg ballots: Pair<Ballot<T>, Int>): Poll<T> = pollOf(mapOf(*ballots))

/** Returns a new poll adding the specified ballot */
operator fun <T : Any> Poll<T>.plus(ballot: Pair<Ballot<T>, Int>): Poll<T> =
        DefaultPoll(ballots + (ballot.first to (ballots[ballot.first].orZero() + ballot.second)))

/** Returns a new poll adding the specified ballot */
operator fun <T : Any> Poll<T>.plus(ballot: Ballot<T>): Poll<T> = this + (ballot to 1)

/**
 * Compute the result of a poll
 *
 * @param method Vote method to use
 * @return A ballot that represent the better the poll
 */
fun <T : Any> Poll<T>.getResult(method: VoteMethod = SchulzeMethod) = method.resultOf(this)

/** Create a new poll with the given ballots collection */
fun <T : Any> Collection<Ballot<T>>.toPoll(): Poll<T> =
        groupBy { it }.mapValues { it.value.size }.toPoll()

/** Create a new poll with the given ballots counts map */
fun <T : Any> Map<Ballot<T>, Int>.toPoll(): Poll<T> =
        pollOf(this)