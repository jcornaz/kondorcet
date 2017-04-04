package kondorcet

import kondorcet.method.CondorcetMethod
import kondorcet.model.*
import org.junit.Assert
import org.junit.Test

abstract class CondorcetProofMethodTest {

    abstract val method: VoteMethod

    @Test
    fun testCondorcetWinner() {
        val poll = pollOf(
                ballot('A', 'C', 'B') to 23,
                ballot('B', 'C', 'A') to 19,
                ballot('C', 'B', 'A') to 16,
                ballot('C', 'A', 'B') to 2
        )

        val expected = ballot('C', 'B', 'A')
        val actual = poll.getResult(method)

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun testExAequoBetweenCandidates() {

        // A > B && B > C && A == C
        val poll = pollOf(
                ballot('A', 'B', 'C') to 5,
                ballot('C', 'A', 'B') to 3,
                ballot('B', 'C', 'A') to 2
        )

        val expected = ballot('A', 'B', 'C')
        val actual = poll.getResult(method)

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun testExAequoOnTheMiddle() {
        var poll = emptyPoll<Char>()

        poll += ballot('A', 'B', 'C', 'D')
        poll += ballot('A', 'C', 'B', 'D')

        val expected = ballot(setOf('A'), setOf('B', 'C'), setOf('D'))
        val actual = poll.getResult(CondorcetMethod)

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun testMissedCandidate() {
        val poll = pollOf(
                ballot('A', 'C', 'B') to 23,
                ballot('B', 'C', 'A') to 19,
                ballot('C', 'B', 'A') to 16,
                ballot('C', 'A', 'B') to 2
        ) + ballot('D', 'A', 'B', 'C')

        val expected = ballot('C', 'B', 'A')
        val actual = poll.getResult(method)

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun testEqualyPreferredCandidate() {

        val poll = pollOf(
                ballot('A', 'B', 'C') to 10,
                ballot(listOf(setOf('A', 'B'), setOf('C'))) to 5,
                ballot('C', 'B', 'A') to 9
        )

        val expected = ballot('A', 'B', 'C')
        val actual = poll.getResult(method)

        Assert.assertEquals(expected, actual)
    }
}