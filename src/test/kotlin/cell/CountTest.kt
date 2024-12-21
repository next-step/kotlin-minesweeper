package cell

import io.kotest.assertions.throwables.shouldThrow
import org.junit.jupiter.api.Test

class CountTest {
    @Test
    fun `값이 0 이하라면 예외가 발생한다`() {
        val value = 0

        shouldThrow<IllegalArgumentException> {
            Count(value)
        }
    }
}
