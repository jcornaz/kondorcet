package condorcet.lib

import org.junit.Assert
import org.junit.Test

class CondorcetTest {

    @Test
    fun testWinner() {
        val vote = Condorcet(setOf("A", "B", "C"))

        IntRange
        vote.vote(listOf("A", "C", "B"), 23)
        vote.vote(listOf("B", "C", "A"), 19)
        vote.vote(listOf("C", "B", "A"), 16)
        vote.vote(listOf("C", "A", "B"), 2)

        val winner = vote.winner()
        Assert.assertNotNull(winner)
        Assert.assertEquals("C", winner)
    }
}