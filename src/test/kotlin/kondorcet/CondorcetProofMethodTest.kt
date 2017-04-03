package kondorcet

import kondorcet.poll.SimplePoll
import org.junit.Assert
import org.junit.Test

abstract class CondorcetProofMethodTest {

    abstract val method: VoteMethod

    @Test
    fun testCondorcetWinner() {
        val poll = SimplePoll<Char>()

        poll.vote(ballot('A', 'C', 'B'), 23)
        poll.vote(ballot('B', 'C', 'A'), 19)
        poll.vote(ballot('C', 'B', 'A'), 16)
        poll.vote(ballot('C', 'A', 'B'), 2)

        val expected = ballot('C', 'B', 'A')
        val actual = poll.result(method)

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun testExAequoBetweenCandidates() {
        val poll = SimplePoll<Char>()

        // A > B && B > C && A == C
        poll.vote(ballot('A', 'B', 'C'), 5)
        poll.vote(ballot('C', 'A', 'B'), 3)
        poll.vote(ballot('B', 'C', 'A'), 2)

        val expected = ballot('A', 'B', 'C')
        val actual = poll.result(method)

        Assert.assertEquals(expected, actual)
    }
}