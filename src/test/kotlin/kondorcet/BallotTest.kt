package kondorcet

import org.junit.Assert.*
import org.junit.Test

class BallotTest {

    @Test
    fun hasDuplicates() {
        assertFalse(Ballot.of(1, 2, 3).hasDuplicates)
        assertTrue(Ballot.of(1, 2, 1).hasDuplicates)
    }

    @Test
    fun Winner() {
        assertNull(Ballot(listOf(setOf(1, 2))).winner)
        assertEquals(1, Ballot.of(1, 2).winner)
    }
}