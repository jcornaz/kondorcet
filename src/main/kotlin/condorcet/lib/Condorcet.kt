package condorcet.lib

import com.google.common.base.Preconditions
import com.google.common.collect.HashBasedTable
import com.google.common.collect.Table

class Condorcet<T : Any>(val candidates: Set<T>) : Poll<T> {

    val matrix: Table<T, T, Int> = HashBasedTable.create()

    override fun vote(ballot: Ballot<T>, multiple: Int) {
        Preconditions.checkArgument(ballot.candidates.all { it in candidates })

        var rest = candidates

        for (candidateList in ballot.orderedCandidates) {
            rest -= candidateList

            for (loser in rest)
                for (candidate in candidateList)
                    matrix[candidate, loser] = multiple + (matrix[candidate, loser] ?: 0)
        }
    }

    fun isWinner(candidate: T) = (candidates - candidate).all { other ->
        (matrix[candidate, other] ?: 0) > (matrix[other, candidate] ?: 0)
    }

    fun winner(): T? = candidates.firstOrNull { isWinner(it) }

    override fun result() = winner()?.let { Ballot.of(it) } ?: Ballot.blank<T>()
}