package kondorcet

import kondorcet.model.*
import org.junit.Assert.assertEquals
import org.junit.Test

class PollsTest {

    @Test
    fun testSimpleUsage() {
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

    @Test
    fun testMapToPoll() {
        val expected = pollOf(
                ballot('A', 'C', 'B') to 23,
                ballot('B', 'C', 'A') to 19,
                ballot('C', 'B', 'A') to 16,
                ballot('C', 'A', 'B') to 5
        )

        val actual = mapOf(
                ballot('A', 'C', 'B') to 23,
                ballot('B', 'C', 'A') to 19,
                ballot('C', 'B', 'A') to 16,
                ballot('C', 'A', 'B') to 5
        ).toPoll()

        assertEquals(expected, actual)
    }

    @Test
    fun testBallotCollectionToPoll() {
        val expected = pollOf(
                ballot('A', 'C', 'B') to 1,
                ballot('B', 'C', 'A') to 1,
                ballot('C', 'B', 'A') to 1,
                ballot('C', 'A', 'B') to 1
        )

        val actual = listOf(
                ballot('A', 'C', 'B'),
                ballot('B', 'C', 'A'),
                ballot('C', 'B', 'A'),
                ballot('C', 'A', 'B')
        ).toPoll()

        assertEquals(expected, actual)
    }
}