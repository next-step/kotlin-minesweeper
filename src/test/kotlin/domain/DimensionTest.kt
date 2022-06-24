package domain

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrow
import org.junit.jupiter.api.Test

class DimensionTest {

    @Test
    fun `너비와 높이 값은 0 보다 커야 한다`() {
        shouldNotThrowAny { Dimension(10, 10) }
        shouldThrow<IllegalArgumentException> { Dimension(0, 10) }
    }
}