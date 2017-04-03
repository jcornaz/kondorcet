@file:JvmName("Polls")

package kondorcet

import kondorcet.method.SchulzeMethod

/**
 * Compute the result of a poll
 *
 * @param method Vote method to use
 * @return A ballot that represent the better the poll
 */
fun <T : Any> Poll<T>.result(method: VoteMethod = SchulzeMethod) = method.resultOf(this)