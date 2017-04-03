package kondorcet

import kondorcet.method.CondorcetMethod
import kondorcet.method.SchulzeMethod
import kondorcet.model.ballot
import kondorcet.model.pollOf
import kondorcet.model.getResult
import org.junit.Assert.assertEquals
import org.junit.Test

class SchulzeMethodTest : CondorcetProofMethodTest() {

    override val method = CondorcetMethod

    /**
     * Test the resultOf when there is condorcet paradox :
     *
     * This test a case given here : https://en.wikipedia.org/wiki/Schulze_method#Satisfied_criteria
     */
    @Test
    fun testParadoxSolving() {
        val poll = pollOf(
                ballot('A', 'C', 'B', 'E', 'D') to 5,
                ballot('A', 'D', 'E', 'C', 'B') to 5,
                ballot('B', 'E', 'D', 'A', 'C') to 8,
                ballot('C', 'A', 'B', 'E', 'D') to 3,
                ballot('C', 'A', 'E', 'B', 'D') to 7,
                ballot('C', 'B', 'A', 'D', 'E') to 2,
                ballot('D', 'C', 'E', 'B', 'A') to 7,
                ballot('E', 'B', 'A', 'D', 'C') to 8
        )

        val expected = ballot('E', 'A', 'C', 'B', 'D')
        val actual = poll.getResult(SchulzeMethod)

        assertEquals(expected, actual)
    }
}