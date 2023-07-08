package tdd.domain

import io.kotest.assertions.throwables.shouldThrow
import org.junit.jupiter.api.Test

class ClosedTest {

    @Test
    fun `When get aroundMineCount, Closed should throw exception`() {
        shouldThrow<IllegalStateException> {
            Empty.aroundMineCount()
        }
    }
}
