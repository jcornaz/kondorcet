package kondorcet

import org.junit.Assert.assertEquals
import org.junit.Test

class SchulzeMethodTest {

    @Test
    fun testCondorcetWinner() {
        val poll = SchulzeMethod<Char>()

        poll.vote(ballot('A', 'C', 'B'), 23)
        poll.vote(ballot('B', 'C', 'A'), 19)
        poll.vote(ballot('C', 'B', 'A'), 16)
        poll.vote(ballot('C', 'A', 'B'), 2)

        val expected = ballot('C', 'B', 'A')
        val actual = poll.result()

        assertEquals(expected, actual)
    }

    /**
     * Test the result when there is condorcet paradox :
     *
     * This test a case given here : https://en.wikipedia.org/wiki/Schulze_method#Satisfied_criteria
     */
    @Test
    fun testParadoxSolving() {
        val poll = SchulzeMethod<Char>()

        poll.vote(ballot('A', 'C', 'B', 'E', 'D'), 5)
        poll.vote(ballot('A', 'D', 'E', 'C', 'B'), 5)
        poll.vote(ballot('B', 'E', 'D', 'A', 'C'), 8)
        poll.vote(ballot('C', 'A', 'B', 'E', 'D'), 3)
        poll.vote(ballot('C', 'A', 'E', 'B', 'D'), 7)
        poll.vote(ballot('C', 'B', 'A', 'D', 'E'), 2)
        poll.vote(ballot('D', 'C', 'E', 'B', 'A'), 7)
        poll.vote(ballot('E', 'B', 'A', 'D', 'C'), 8)

        val expected = ballot('E', 'A', 'C', 'B', 'D')
        val actual = poll.result()

        assertEquals(expected, actual)
    }

    @Test
    fun testExAequoOnTheMiddle() {
        val poll = SchulzeMethod<Char>()

        poll.vote(ballot('A', 'B', 'C', 'D'))
        poll.vote(ballot('A', 'C', 'B', 'D'))

        val expected = ballot(setOf('A'), setOf('B', 'C'), setOf('D'))
        val actual = poll.result()

        assertEquals(expected, actual)
    }
}