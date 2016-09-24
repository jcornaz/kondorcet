package kondorcet

import kondorcet.method.CondorcetMethod
import org.junit.Assert
import org.junit.Test

class CheckedPollTest {

    @Test
    fun simpleUsage() {
        val poll = CheckedPoll<Char>()

        poll.vote(ballot('A', 'C', 'B'), 23)
        poll.vote(ballot('B', 'C', 'A'), 19)
        poll.vote(ballot('C', 'B', 'A'), 16)
        poll.vote(ballot('C', 'A', 'B'), 2)

        val expected = ballot('C', 'B', 'A')
        val actual = poll.result(CondorcetMethod)

        Assert.assertEquals(expected, actual)
    }

    @Test(expected = IllegalArgumentException::class)
    fun exceptionOnBallotWithDuplicates() {
        CheckedPoll<Char>().vote(ballot('A', 'C', 'B', 'C'))
    }
}