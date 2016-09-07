package com.toolable.condorcet

import com.google.common.base.Preconditions
import com.google.common.collect.HashBasedTable
import com.google.common.collect.Table

class Condorcet<T : Any>(val candidates: Set<T>) {

    val matrix: Table<T, T, Int> = HashBasedTable.create()

    fun vote(orderedChoices: List<T>, voices: Int = 1) {
        Preconditions.checkArgument(orderedChoices.size == candidates.size)
        Preconditions.checkArgument(orderedChoices.all { it in candidates })

        var rest = candidates

        for (candidate in orderedChoices) {
            rest -= candidate

            if (rest.isNotEmpty()) {
                for (loser in rest)
                    matrix[candidate, loser] = voices + (matrix[candidate, loser] ?: 0)
            }
        }
    }

    fun isWinner(candidate: T) = (candidates - candidate).all { other ->
        (matrix[candidate, other] ?: 0) > (matrix[other, candidate] ?: 0)
    }

    fun winner(): T? = candidates.firstOrNull { isWinner(it) }
}