package domain.model

import io.kotest.assertions.throwables.shouldThrow
import org.junit.jupiter.api.Test

class PointTest {
    @Test
    fun `x좌표가 음수 값일 때 IllegalArgumentException 발생`() {
        shouldThrow<IllegalArgumentException> {
            X(-1)
        }
    }

    @Test
    fun `y좌표가 음수 값일 때 IllegalArgumentException 발생`() {
        shouldThrow<IllegalArgumentException> {
            Y(-1)
        }
    }

    @Test
    fun `좌표에 음수 값이 있을 때 IllegalArgumentException 발생`() {
        shouldThrow<IllegalArgumentException> {
            Point.from(-1, -1)
        }
    }
}
