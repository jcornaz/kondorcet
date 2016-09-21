package condorcet.lib

import org.junit.Assert.assertEquals
import org.junit.Test

class SchulzeMethodTest {

    @Test
    fun testCondorcetWinner() {
        val poll = SchulzeMethod<Char>()

        poll.vote(Ballot.of('A', 'C', 'B'), 23)
        poll.vote(Ballot.of('B', 'C', 'A'), 19)
        poll.vote(Ballot.of('C', 'B', 'A'), 16)
        poll.vote(Ballot.of('C', 'A', 'B'), 2)

        val expected = listOf('C', 'B', 'A').map { setOf(it) }
        val actual = poll.result().orderedCandidates

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

        poll.vote(Ballot.of('A', 'C', 'B', 'E', 'D'), 5)
        poll.vote(Ballot.of('A', 'D', 'E', 'C', 'B'), 5)
        poll.vote(Ballot.of('B', 'E', 'D', 'A', 'C'), 8)
        poll.vote(Ballot.of('C', 'A', 'B', 'E', 'D'), 3)
        poll.vote(Ballot.of('C', 'A', 'E', 'B', 'D'), 7)
        poll.vote(Ballot.of('C', 'B', 'A', 'D', 'E'), 2)
        poll.vote(Ballot.of('D', 'C', 'E', 'B', 'A'), 7)
        poll.vote(Ballot.of('E', 'B', 'A', 'D', 'C'), 8)

        val expected = listOf('E', 'A', 'C', 'B', 'D').map { setOf(it) }
        val actual = poll.result().orderedCandidates

        assertEquals(expected, actual)
    }
}