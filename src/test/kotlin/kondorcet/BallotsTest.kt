package kondorcet

import kondorcet.model.ballot
import kondorcet.model.emptyBallot
import kondorcet.model.with
import org.junit.Assert.*
import org.junit.Test

class BallotsTest {

    @Test
    fun testEmptyBallot() {
        assertEquals(emptyList<Set<Char>>(), emptyBallot<Char>().orderedCandidates)
    }

    @Test
    fun testNoDuplicate() {
        assertEquals(ballot(1, 2, 3), ballot(1, 2, 3, 3))
        assertEquals(listOf(setOf(1), setOf(2), setOf(3)), ballot(1, 2, 3, 3).orderedCandidates)
    }

    @Test
    fun testWinner() {
        assertNull(ballot(setOf(1, 2)).winner)
        assertEquals(1, ballot(1, 2).winner)
    }

    @Test
    fun testWith() {
        val result = ballot(1, 2, 3) with listOf(1, 2, 3, 4, 5)
        assertTrue(result is Ballot<Int>)
        assertEquals(listOf(setOf(1), setOf(2), setOf(3), setOf(4, 5)), result.orderedCandidates)
    }
}