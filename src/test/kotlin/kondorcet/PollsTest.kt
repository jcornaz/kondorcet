package kondorcet

import kondorcet.model.ballot
import kondorcet.model.emptyPoll
import kondorcet.model.plus
import org.junit.Assert.assertEquals
import org.junit.Test

class PollsTest {

    @Test
    fun simpleUsage() {
        var poll = emptyPoll<Char>()

        poll += ballot('A', 'C', 'B') to 23
        poll += ballot('B', 'C', 'A') to 19
        poll += ballot('C', 'B', 'A') to 16
        poll += ballot('C', 'A', 'B') to 2
        poll += ballot('C', 'A', 'B') to 3

        assertEquals(mapOf(
                ballot('A', 'C', 'B') to 23,
                ballot('B', 'C', 'A') to 19,
                ballot('C', 'B', 'A') to 16,
                ballot('C', 'A', 'B') to 5
        ), poll.ballots)
    }
}