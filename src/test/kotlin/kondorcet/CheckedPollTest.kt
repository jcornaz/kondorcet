package kondorcet

import kondorcet.poll.CheckedPoll
import org.junit.Assert.assertEquals
import org.junit.Test

class CheckedPollTest {

    @Test
    fun simpleUsage() {
        val poll = CheckedPoll<Char>()

        poll.vote(ballot('A', 'C', 'B'), 23)
        poll.vote(ballot('B', 'C', 'A'), 19)
        poll.vote(ballot('C', 'B', 'A'), 16)
        poll.vote(ballot('C', 'A', 'B'), 2)
        poll.vote(ballot('C', 'A', 'B'), 3)

        assertEquals(mapOf(
                ballot('A', 'C', 'B') to 23,
                ballot('B', 'C', 'A') to 19,
                ballot('C', 'B', 'A') to 16,
                ballot('C', 'A', 'B') to 5
        ), poll.ballots)
    }

    @Test(expected = IllegalArgumentException::class)
    fun exceptionOnBallotWithDuplicates() {
        CheckedPoll<Char>().vote(ballot('A', 'C', 'B', 'C'))
    }
}