package kondorcet.method

import kable.*
import kondorcet.*

/**
 * Abstraction of [VoteMethod] that are based on graphs
 */
abstract class GraphBasedMethod : VoteMethod {

    override fun <T : Any> resultOf(poll: Poll<T>): Ballot<T> {
        var graph = emptyTable<T, T, Int>()

        for (ballot in poll.ballots)
            graph += ballot.toPair()

        return victoriesTableOf(graph).toBallot()
    }

    operator fun <T : Any> Table<T, T, Int>.plus(ballots: Pair<Ballot<T>, Int>): Table<T, T, Int> {
        val (ballot, count) = ballots
        var result = this
        var losers = ballot.orderedCandidates

        while (losers.isNotEmpty()) {
            val winnerSet = losers.first()
            losers = losers.subList(1, losers.size)

            for (loser in losers.flatten()) {
                for (winner in winnerSet) {
                    result += entry(winner, loser, result[winner, loser].orZero() + count)
                }
            }
        }

        return result
    }

    /**
     * Compute the result graph from the data graph
     *
     * @receiver A graph adjacency matrix where vertices are candidates and any edge from x to y of weight w represent w victories of x against y.
     * @return A graph adjacency matrix where every vertices are candidates and any edge from x to y represent the victory state of x against y (true = victory, false = defeat)
     */
    abstract fun <T : Any> victoriesTableOf(graph: Table<T, T, Int>): Table<T, T, Boolean>

    open fun <T : Any> Table<T, T, Boolean>.toBallot(): Ballot<T> {

        val candidates = rows + columns

        val (table, winners) = extractCandidates({ victories -> victories.all { it } }) { list, set -> list + listOf(set) }
        val (_, losers) = table.extractCandidates({ victories -> victories.none { it } }) { list, set -> listOf(set) + list }
        val others = (candidates - winners.flatten() - losers.flatten()).let { if (it.isEmpty()) emptyList() else listOf(it.toSet()) }

        return ballot(winners + others + losers)
    }

    open fun <T : Any> Table<T, T, Boolean>.extractCandidates(
            predicate: (Collection<Boolean>) -> Boolean,
            merge: (List<Set<T>>, Set<T>) -> List<Set<T>>
    ): Pair<Table<T, T, Boolean>, List<Set<T>>> {

        var table = this
        var candidates = rows + columns
        var result = emptyList<Set<T>>()

        var nextCandidates = candidates.filter { predicate((getRow(it) - it).values) }
        while (!table.isEmpty() && !nextCandidates.isEmpty()) {

            for (candidate in nextCandidates)
                table = table.minusRow(candidate).minusColumn(candidate)

            result += merge(result, nextCandidates.toSet())
            candidates -= nextCandidates
            nextCandidates = candidates.filter { predicate((getRow(it) - it).values) }
        }

        return table to result
    }
}