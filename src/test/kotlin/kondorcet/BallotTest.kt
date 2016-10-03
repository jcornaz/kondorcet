package kondorcet

import org.junit.Assert.*
import org.junit.Test

class BallotTest {

    @Test
    fun hasDuplicates() {
        assertFalse(ballot(1, 2, 3).hasDuplicates())
        assertTrue(ballot(1, 2, 1).hasDuplicates())
    }

    @Test
    fun Winner() {
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