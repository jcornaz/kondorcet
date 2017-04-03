package kondorcet

import kondorcet.method.RelativeMajorityMethod
import kondorcet.model.ballot
import kondorcet.model.pollOf
import kondorcet.model.getResult
import org.junit.Assert
import org.junit.Test

class RelativeMajorityMethodTest {

    @Test
    fun testStandardCase() {
        val poll = pollOf(
                ballot('A', 'C', 'B') to 23,
                ballot('B', 'C', 'A') to 19,
                ballot('C', 'B', 'A') to 16,
                ballot('C', 'A', 'B') to 2
        )

        val expected = ballot('A', 'B', 'C')
        val actual = poll.getResult(RelativeMajorityMethod)

        Assert.assertEquals(expected, actual)
    }
}