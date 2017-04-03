package kondorcet

import kondorcet.method.RelativeMajorityMethod
import kondorcet.poll.SimplePoll
import org.junit.Assert
import org.junit.Test

class RelativeMajorityMethodTest {

    @Test
    fun testStandardCase() {
        val poll = SimplePoll<Char>()

        poll.vote(ballot('A', 'C', 'B'), 23)
        poll.vote(ballot('B', 'C', 'A'), 19)
        poll.vote(ballot('C', 'B', 'A'), 16)
        poll.vote(ballot('C', 'A', 'B'), 2)

        val expected = ballot('A', 'B', 'C')
        val actual = poll.result(RelativeMajorityMethod)

        Assert.assertEquals(expected, actual)
    }
}