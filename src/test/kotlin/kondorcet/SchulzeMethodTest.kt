package kondorcet

import kondorcet.method.CondorcetMethod
import kondorcet.method.SchulzeMethod
import kondorcet.poll.SimplePoll
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
        val poll = SimplePoll<Char>()

        poll.vote(ballot('A', 'C', 'B', 'E', 'D'), 5)
        poll.vote(ballot('A', 'D', 'E', 'C', 'B'), 5)
        poll.vote(ballot('B', 'E', 'D', 'A', 'C'), 8)
        poll.vote(ballot('C', 'A', 'B', 'E', 'D'), 3)
        poll.vote(ballot('C', 'A', 'E', 'B', 'D'), 7)
        poll.vote(ballot('C', 'B', 'A', 'D', 'E'), 2)
        poll.vote(ballot('D', 'C', 'E', 'B', 'A'), 7)
        poll.vote(ballot('E', 'B', 'A', 'D', 'C'), 8)

        val expected = ballot('E', 'A', 'C', 'B', 'D')
        val actual = poll.result(SchulzeMethod)

        assertEquals(expected, actual)
    }

    @Test
    fun testExAequoOnTheMiddle() {
        val poll = SimplePoll<Char>()

        poll.vote(ballot('A', 'B', 'C', 'D'))
        poll.vote(ballot('A', 'C', 'B', 'D'))

        val expected = ballot(setOf('A'), setOf('B', 'C'), setOf('D'))
        val actual = poll.result(SchulzeMethod)

        assertEquals(expected, actual)
    }
}