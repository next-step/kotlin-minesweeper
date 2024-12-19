package cell

import io.kotest.assertions.throwables.shouldThrow
import org.junit.jupiter.api.Test

class CoordinateTest {
    @Test
    fun `좌표 값 생성 시 음수가 있으면 예외가 발생한다`() {
        val value = -1

        shouldThrow<IllegalArgumentException> {
            CoordinateValue(value)
        }
    }
}
