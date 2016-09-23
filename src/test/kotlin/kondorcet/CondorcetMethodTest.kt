package kondorcet

import org.junit.Assert
import org.junit.Test

class CondorcetMethodTest {

    @Test
    fun testCondorcetWinner() {
        val poll = CondorcetMethod<Char>()

        poll.vote(Ballot.of('A', 'C', 'B'), 23)
        poll.vote(Ballot.of('B', 'C', 'A'), 19)
        poll.vote(Ballot.of('C', 'B', 'A'), 16)
        poll.vote(Ballot.of('C', 'A', 'B'), 2)

        val expected = listOf('C', 'B', 'A').map { setOf(it) }
        val actual = poll.result().orderedCandidates

        Assert.assertEquals(expected, actual)
    }
}