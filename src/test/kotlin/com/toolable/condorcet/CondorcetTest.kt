package com.toolable.condorcet

import org.junit.Assert
import org.junit.Test

class CondorcetTest {

    @Test
    fun testWinner() {
        val scrutin = Condorcet(setOf("A", "B", "C"))

        IntRange
        scrutin.vote(listOf("A", "C", "B"), 23)
        scrutin.vote(listOf("B", "C", "A"), 19)
        scrutin.vote(listOf("C", "B", "A"), 16)
        scrutin.vote(listOf("C", "A", "B"), 2)

        val winner = scrutin.winner()
        Assert.assertNotNull(winner)
        Assert.assertEquals("C", winner)
    }
}