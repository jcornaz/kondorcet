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
}