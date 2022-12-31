package minesweeper.model

import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.Test

internal class PointTest {
    @Test
    fun `x 좌표가 0 이상이어야 한다`() {
        val x = -1
        val y = 10

        assertThatExceptionOfType(IllegalArgumentException::class.java).isThrownBy {
            Point(x, y)
        }
            .withMessage("x 좌표는 0 이상이어야 합니다.")

    }

    @Test
    fun `y 좌표가 0 이상이어야 한다2`() {
        val x = 10
        val y = -1

        assertThatExceptionOfType(IllegalArgumentException::class.java).isThrownBy {
            Point(x, y)
        }
            .withMessage("y 좌표는 0 이상이어야 합니다.")

    }
}
